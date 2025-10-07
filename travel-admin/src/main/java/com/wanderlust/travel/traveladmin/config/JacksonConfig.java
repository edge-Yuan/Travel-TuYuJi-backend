package com.wanderlust.travel.traveladmin.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Jackson配置类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Configuration
public class JacksonConfig {

    /**
     * 支持多种日期时间格式的ObjectMapper
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        
        // 创建JavaTimeModule
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        
        // 创建自定义模块处理LocalDateTime
        SimpleModule customModule = new SimpleModule();
        customModule.addDeserializer(LocalDateTime.class, new FlexibleLocalDateTimeDeserializer());
        
        mapper.registerModule(javaTimeModule);
        mapper.registerModule(customModule);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        return mapper;
    }
    
    /**
     * 灵活的LocalDateTime反序列化器
     * 支持多种日期时间格式
     */
    public static class FlexibleLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        
        private static final DateTimeFormatter[] FORMATTERS = {
            // ISO 8601 格式 (带Z)
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"),
            // 标准格式
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
            // 日期格式
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            // 带时间的格式
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        };
        
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String dateTimeString = p.getText().trim();
            
            if (dateTimeString == null || dateTimeString.isEmpty()) {
                return null;
            }
            
            // 尝试解析ISO 8601格式 (带Z)
            if (dateTimeString.endsWith("Z")) {
                try {
                    Instant instant = Instant.parse(dateTimeString);
                    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                } catch (DateTimeParseException e) {
                    // 继续尝试其他格式
                }
            }
            
            // 尝试各种格式
            for (DateTimeFormatter formatter : FORMATTERS) {
                try {
                    return LocalDateTime.parse(dateTimeString, formatter);
                } catch (DateTimeParseException e) {
                    // 继续尝试下一个格式
                }
            }
            
            throw new IOException("无法解析日期时间字符串: " + dateTimeString);
        }
    }
}
