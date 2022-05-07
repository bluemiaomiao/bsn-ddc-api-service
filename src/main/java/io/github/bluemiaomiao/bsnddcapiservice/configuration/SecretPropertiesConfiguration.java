package io.github.bluemiaomiao.bsnddcapiservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:secret.properties")
@ConfigurationProperties(prefix = "secret")
public class SecretPropertiesConfiguration {
    private String privateKey = null;
}
