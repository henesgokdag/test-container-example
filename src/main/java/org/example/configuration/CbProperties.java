package org.example.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.couchbase")
@Configuration
@Data
public class CbProperties {
    private String host;
    private String username;
    private String password;
    private String bucket;
}
