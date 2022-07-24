package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CbConfig  extends AbstractCouchbaseConfiguration {
    private final CbProperties cbProperties;

    public CbConfig(CbProperties cbProperties) {
        this.cbProperties = cbProperties;
    }

    @Override
    public String getConnectionString() {
        return cbProperties.getHost();
    }

    @Override
    public String getUserName() {
        return cbProperties.getUsername();
    }

    @Override
    public String getPassword() {
        return cbProperties.getPassword();
    }

    @Override
    public String getBucketName() {
        return cbProperties.getBucket();
    }

    @Bean
    @Override
    public CustomConversions customConversions(){ return super.customConversions(); }
}
