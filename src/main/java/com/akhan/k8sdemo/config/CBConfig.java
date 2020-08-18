package com.akhan.k8sdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties("couchbase-config")
@RefreshScope
@Data
public class CBConfig {
    private String cburl;
    private String userid;
    private String password;
    private String bucketname;
    private String sslKeystoreFilePath;
    private String sslKeyStorePassword;
}
