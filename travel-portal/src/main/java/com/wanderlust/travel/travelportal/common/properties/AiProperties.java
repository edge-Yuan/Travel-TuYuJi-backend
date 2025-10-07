package com.wanderlust.travel.travelportal.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.ai")
@Data
public class AiProperties {

    /**
     * DeepSeek API Key，建议通过环境变量注入
     */
    private String apiKey;

    /**
     * DeepSeek 基础地址，例如：https://api.deepseek.com/v1/chat/completions
     */
    private String baseUrl;

    /**
     * 模型名称，例如 deepseek-chat 或 deepseek-reasoner
     */
    private String model;

    /**
     * 请求超时时间（毫秒）
     */
    private int timeoutMs;

    /**
     * 最大重试次数
     */
    private int maxRetries;

    /**
     * 简易限流：每秒允许的请求数
     */
    private double qps;

    /**
     * 开发态是否跳过 TLS 校验（仅限本地排障，生产请勿开启）
     */
    private boolean insecureSkipTlsVerify;

    /**
     * 代理地址（可选），如 http://127.0.0.1:7890 或 http://corp-proxy:8080
     */
    private String proxyUrl;
}


