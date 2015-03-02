package org.vaadin.risto.microservices.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class StudentApplication extends RepositoryRestMvcConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Student.class);
    }
}
