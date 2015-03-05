package org.vaadin.risto.microservices.rest.student;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;

@Component
public class StudentsClient {

    @Autowired
    RestTemplate template;

    @PostConstruct
    public void setupMapping() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
        converter.setObjectMapper(mapper);

        template.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(converter));
    }

    public Collection<StudentResource> getStudents() {

        ResponseEntity<StudentResources> exchange =
                template.exchange(
                        "http://students-service/students",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<StudentResources>() {
                        });

        return exchange.getBody().getContent();
    }
}
