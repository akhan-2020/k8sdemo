package com.akhan.k8sdemo;

import com.akhan.k8sdemo.config.CBConfig;
import com.couchbase.client.core.env.PasswordAuthenticator;
import com.couchbase.client.core.env.SecurityConfig;
import com.couchbase.client.java.env.ClusterEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import com.couchbase.client.core.env.Authenticator;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.springframework.data.couchbase.core.mapping.KeySettings.build;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Autowired
    CBConfig cbConfig;

    @Override
    public String getConnectionString() {
        return cbConfig.getCburl();
    }

    @Override
    public String getUserName() {
        return cbConfig.getUserid();
    }

    @Override
    public String getPassword() {
        return cbConfig.getPassword();
    }

    @Override
    public String getBucketName() {
        return cbConfig.getBucketname();
    }


    @Override
    public ClusterEnvironment couchbaseClusterEnvironment() {
        String trustType = null;
        Optional<String> opt = Optional.ofNullable(trustType);

        ClusterEnvironment env = ClusterEnvironment.builder()
                .securityConfig(SecurityConfig
                        .enableTls(true).trustStore(Paths.get(cbConfig.getSslKeystoreFilePath()),
                                cbConfig.getSslKeyStorePassword(),opt))
                        .build();
        return env;
    }

}
