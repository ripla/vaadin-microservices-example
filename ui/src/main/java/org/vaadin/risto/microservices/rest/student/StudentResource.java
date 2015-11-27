package org.vaadin.risto.microservices.rest.student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentResource extends ResourceSupport {

    private String name;
}
