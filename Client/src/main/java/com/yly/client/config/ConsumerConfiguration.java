package com.yly.client.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2021/7/12 下午5:35
 * @since 1.0
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.yly.client.service")
@PropertySource("classpath:dubbo-consumer.properties")
public class ConsumerConfiguration {
}