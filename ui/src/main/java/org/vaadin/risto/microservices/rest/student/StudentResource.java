package org.vaadin.risto.microservices.rest.student;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class StudentResource extends ResourceSupport {

    private String name;
}
