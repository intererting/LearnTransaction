package com.yly.server.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2021/7/12 下午4:31
 * @since 1.0
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.yly.server.service")
@PropertySource("classpath:dubbo-provider.properties")
public class ProviderConfiguration {
}
