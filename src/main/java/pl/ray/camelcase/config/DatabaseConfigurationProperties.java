package pl.ray.camelcase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("database")
public class DatabaseConfigurationProperties {

    private String host;

    private String database;

    private String username;

    private String password;

    private boolean quote;

}
