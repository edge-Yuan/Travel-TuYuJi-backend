package com.wanderlust.travel.travelportal.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MyFastGeneratorConfiguration {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/tour_management_system?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true", "root", "137063011azy")
                .globalConfig(builder -> {
                    builder.author("wanderlust") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/edge-zhang/backend/travel-crm"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wanderlust.travel.travelportal") // 设置父包名
                            .moduleName("travel-portal") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/edge-zhang/backend/travel-crm"));// 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_announcement"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
