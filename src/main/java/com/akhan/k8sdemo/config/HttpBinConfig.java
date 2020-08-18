package com.akhan.k8sdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("httpbin-config")
@RefreshScope
@Data
public class HttpBinConfig {
    private String url;
    private String api;
}
