package com.wanderlust.travel.traveladmin;

import com.wanderlust.travel.traveladmin.config.FileUploadConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 旅游管理系统后台管理模块启动类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@SpringBootApplication
@EnableConfigurationProperties({FileUploadConfig.class})
public class TravelAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAdminApplication.class, args);
        System.out.println("=================================");
        System.out.println("旅游管理系统后台管理模块启动成功！");
        System.out.println("访问地址：http://localhost:8082/travel-admin");
        System.out.println("=================================");
    }

}
