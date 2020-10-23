package pl.ray.camelcase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.flyway")
class FlywayConfigurationProperties {

    String user;

    String password;

    String url;

}