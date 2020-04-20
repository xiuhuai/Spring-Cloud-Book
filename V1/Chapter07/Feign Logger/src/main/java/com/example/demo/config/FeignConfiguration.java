package com.example.demo.config;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: 配置类，配置记录日志的类别。
 *NONE：不记录（默认）。
 * BASIC：只记录请求方法和URL以及响应状态码和执行时间。
 * HEADERS：记录基本信息以及请求和响应标题。
 * FULL：记录请求和响应的标题，正文和元数据。
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}