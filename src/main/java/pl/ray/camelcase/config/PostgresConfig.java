package pl.ray.camelcase.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class PostgresConfig extends AbstractR2dbcConfiguration {

    private final DatabaseConfigurationProperties postgresProperties;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(postgresProperties.getHost())
                        .username(postgresProperties.getUsername())
                        .password(postgresProperties.getPassword())
                        .database(postgresProperties.getDatabase())
                        .build()
        );
    }

    @Bean
    ReactiveTransactionManager reactiveTransactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    TransactionalOperator transactionalOperator(ReactiveTransactionManager reactiveTransactionManager) {
        return TransactionalOperator.create(reactiveTransactionManager);
    }

    @Bean
    @Override
    public R2dbcMappingContext r2dbcMappingContext(Optional<NamingStrategy> namingStrategy, R2dbcCustomConversions r2dbcCustomConversions) {
        R2dbcMappingContext context = new R2dbcMappingContext();
        context.setSimpleTypeHolder(r2dbcCustomConversions.getSimpleTypeHolder());
        context.setForceQuote(postgresProperties.isQuote());
        return context;
    }

}

