package org.vaadin.risto.microservices;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("heroku")
public class HerokuConfiguration extends AbstractCloudConfig {

    @Bean
    public ConnectionFactory rabbitFactory() {
        return connectionFactory().rabbitConnectionFactory();
    }
}
