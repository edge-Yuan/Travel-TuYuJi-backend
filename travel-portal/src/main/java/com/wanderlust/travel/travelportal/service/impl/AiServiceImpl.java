package com.wanderlust.travel.travelportal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanderlust.travel.travelportal.common.properties.AiProperties;
import com.wanderlust.travel.travelportal.common.utils.HttpClientUtil;
import com.wanderlust.travel.travelportal.dto.ai.PlanRouteRequest;
import com.wanderlust.travel.travelportal.service.IAiService;
import com.wanderlust.travel.travelportal.vo.ai.PlanRouteResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Semaphore;

@Service
@Slf4j
public class AiServiceImpl implements IAiService {

    @Autowired
    private AiProperties aiProperties;

    private final Semaphore rateLimiter = new Semaphore(5);

    @Override
    public PlanRouteResponse planRoute(PlanRouteRequest request) {
        auditLog("AI_PLAN_ROUTE_REQUEST", request);

        int attempts = 0;
        Exception lastEx = null;
        int maxRetries = Math.max(0, aiProperties.getMaxRetries());

        while (attempts <= maxRetries) {
            try {
                acquirePermit();
                try {
                    String payload = buildDeepSeekPayload(request);
                    String response = doHttpPostJsonWithAuth(aiProperties.getBaseUrl(), payload);
                    PlanRouteResponse parsed = parseDeepSeekResponse(response, request.getDays());
                    auditLog("AI_PLAN_ROUTE_RESPONSE", truncate(response));
                    return parsed;
                } finally {
                    rateLimiter.release();
                }
            } catch (Exception e) {
                lastEx = e;
                log.warn("DeepSeek 调用失败，第{}次: {}", attempts + 1, e.getMessage());
                sleepBackoff(attempts);
                attempts++;
            }
        }

        if (lastEx != null) {
            log.error("DeepSeek 多次重试失败: {}", lastEx.getMessage(), lastEx);
        }
        throw new RuntimeException(lastEx != null ? lastEx.getMessage() : "AI 调用失败");
    }

    private void acquirePermit() {
        try {
            rateLimiter.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void sleepBackoff(int attempt) {
        try {
            long base = 300L;
            Thread.sleep(base * (long) Math.pow(2, attempt));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private String buildDeepSeekPayload(PlanRouteRequest req) {
        String systemPrompt = "你是资深旅行规划师，请根据目的地、天数、预算、偏好和住宿偏好，规划每日四段活动（上午/中午/下午/晚上），以 JSON 返回，结构为 {\"days\":[{\"activities\":[\"上午xxx\", \"中午xxx\", \"下午xxx\", \"晚上xxx\"]}, ...]}，不要输出多余说明。";

        JSONObject body = new JSONObject();
        body.put("model", Optional.ofNullable(aiProperties.getModel()).orElse("deepseek-chat"));
        JSONArray messages = new JSONArray();
        JSONObject sys = new JSONObject();
        sys.put("role", "system");
        sys.put("content", systemPrompt);
        messages.add(sys);

        JSONObject user = new JSONObject();
        user.put("role", "user");
        user.put("content", String.format(Locale.CHINA,
                "目的地: %s\n天数: %d\n预算: %s\n偏好: %s\n住宿: %s",
                req.getDestination(),
                req.getDays(),
                req.getBudget(),
                req.getInterests() == null ? "" : String.join(",", req.getInterests()),
                req.getAccommodation()));
        messages.add(user);

        body.put("messages", messages);
        body.put("temperature", 0.7);
        body.put("response_format", Collections.singletonMap("type", "json_object"));
        return body.toJSONString();
    }

    private String doHttpPostJsonWithAuth(String url, String json) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String rawKey = Optional.ofNullable(aiProperties.getApiKey()).orElse("").trim();
        if (rawKey.isEmpty()) {
            rawKey = loadApiKeyFromDefaultLocations();
        }
        if (rawKey == null || rawKey.trim().isEmpty()) {
            throw new IOException("DeepSeek API Key 未配置（请在模块 travel-portal 下创建 ai.key 或设置环境变量 DEEPSEEK_API_KEY）");
        }
        String authHeader = rawKey.startsWith("Bearer ") ? rawKey : (rawKey.startsWith("sk-") ? "Bearer " + rawKey : "Bearer " + rawKey);
        headers.put("Authorization", authHeader);
        headers.put("Content-Type", "application/json");

        try {
            java.net.http.HttpClient.Builder builder = java.net.http.HttpClient.newBuilder()
                    .version(java.net.http.HttpClient.Version.HTTP_1_1)
                    .connectTimeout(java.time.Duration.ofMillis(aiProperties.getTimeoutMs()));

            // 代理（可选）
            if (aiProperties.getProxyUrl() != null && !aiProperties.getProxyUrl().isEmpty()) {
                URI proxyUri = URI.create(aiProperties.getProxyUrl());
                ProxySelector proxySelector = ProxySelector.of(new InetSocketAddress(proxyUri.getHost(), proxyUri.getPort()));
                builder.proxy(proxySelector);
            }

            // 开发态跳过 TLS 校验（仅本地排障）
            if (aiProperties.isInsecureSkipTlsVerify()) {
                TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                            public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
                        }
                };
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustAllCerts, new SecureRandom());
                builder.sslContext(sslContext);
            }

            java.net.http.HttpClient client = builder.build();

            java.net.http.HttpRequest.Builder reqBuilder = java.net.http.HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(java.time.Duration.ofMillis(aiProperties.getTimeoutMs()))
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8));
            headers.forEach(reqBuilder::header);

            java.net.http.HttpRequest httpRequest = reqBuilder.build();
            java.net.http.HttpResponse<String> response = client.send(httpRequest, java.net.http.HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            int status = response.statusCode();
            String result = response.body();
            if (status < 200 || status >= 300) {
                throw new IOException("HTTP " + status + ": " + result);
            }
            return result;
        } catch (Exception e) {
            if (e instanceof IOException) throw (IOException) e;
            throw new IOException(e.getMessage(), e);
        }
    }

    private String loadApiKeyFromDefaultLocations() {
        // 优先读取运行目录（可能是项目根）下的 ai.key
        try {
            Path p1 = Paths.get("ai.key");
            if (Files.exists(p1)) {
                String key = new String(Files.readAllBytes(p1), StandardCharsets.UTF_8).trim();
                if (!key.isEmpty()) return key;
            }
        } catch (Exception ignore) {}

        // 读取项目根下的 travel-portal/ai.key（适配从项目根启动的情况）
        try {
            Path p2 = Paths.get("travel-portal", "ai.key");
            if (Files.exists(p2)) {
                String key = new String(Files.readAllBytes(p2), StandardCharsets.UTF_8).trim();
                if (!key.isEmpty()) return key;
            }
        } catch (Exception ignore) {}

        // 兼容用户主目录 ~/.secrets/travel-portal.env 中的 DEEPSEEK_API_KEY=...
        try {
            Path p3 = Paths.get(System.getProperty("user.home"), ".secrets", "travel-portal.env");
            if (Files.exists(p3)) {
                for (String line : Files.readAllLines(p3, StandardCharsets.UTF_8)) {
                    String s = line.trim();
                    if (s.startsWith("DEEPSEEK_API_KEY=")) {
                        String v = s.substring("DEEPSEEK_API_KEY=".length()).trim();
                        if (!v.isEmpty()) return v;
                    }
                }
            }
        } catch (Exception ignore) {}

        return null;
    }

    private PlanRouteResponse parseDeepSeekResponse(String resp, Integer expectedDays) {
        try {
            JSONObject root = JSON.parseObject(resp);
            // DeepSeek 兼容 OpenAI: data -> choices[0].message.content
            String content;
            if (root.containsKey("choices")) {
                JSONArray choices = root.getJSONArray("choices");
                if (choices != null && !choices.isEmpty()) {
                    JSONObject msg = choices.getJSONObject(0).getJSONObject("message");
                    content = msg.getString("content");
                } else {
                    throw new IllegalStateException("AI 无返回内容");
                }
            } else {
                // 已是 JSON 对象
                content = resp;
            }

            JSONObject json = JSON.parseObject(content);
            JSONArray days = json.getJSONArray("days");
            if (days == null) {
                throw new IllegalStateException("AI 返回缺少 days 字段");
            }

            PlanRouteResponse result = new PlanRouteResponse();
            List<PlanRouteResponse.DayPlan> dayPlans = new ArrayList<>();
            for (int i = 0; i < days.size(); i++) {
                JSONObject d = days.getJSONObject(i);
                JSONArray activities = d.getJSONArray("activities");
                List<String> acts = new ArrayList<>();
                if (activities != null) {
                    for (int j = 0; j < activities.size(); j++) {
                        acts.add(Objects.toString(activities.getString(j), ""));
                    }
                }
                PlanRouteResponse.DayPlan dp = new PlanRouteResponse.DayPlan();
                dp.setActivities(acts);
                dayPlans.add(dp);
            }

            // 如 AI 返回天数不足，简单补齐空活动
            if (expectedDays != null && expectedDays > dayPlans.size()) {
                for (int k = dayPlans.size(); k < expectedDays; k++) {
                    PlanRouteResponse.DayPlan dp = new PlanRouteResponse.DayPlan();
                    dp.setActivities(Arrays.asList("", "", "", ""));
                    dayPlans.add(dp);
                }
            }

            result.setDays(dayPlans);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("解析 AI 返回失败: " + e.getMessage(), e);
        }
    }

    private void auditLog(String tag, Object payload) {
        try {
            log.info("[AI_AUDIT] {} ts={} payload={}", tag, Instant.now().toEpochMilli(), JSON.toJSONString(payload));
        } catch (Exception ignore) {
        }
    }

    private String truncate(String s) {
        if (s == null) return null;
        int max = 4000;
        return s.length() > max ? s.substring(0, max) + "..." : s;
    }
}


