package org.vaadin.risto.microservices.rest.student;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StudentsClient {

    private static final String URL = "http://students-service/students?page={page}&size={size}";

    @Autowired
    RestTemplate template;

    public Stream<Student> getStudents(int offset, int limit) {
        Map<String, Integer> params = new HashMap<>();
        params.put("page", offset / limit);
        params.put("size", limit);

        ResponseEntity<Resources<Resource<Student>>> studentResponse = template
                .exchange(URL, HttpMethod.GET, null,
                        new TypeReferences.ResourcesType<Resource<Student>>(),
                        params);

        return studentResponse.getBody().getContent().stream()
                .map(Resource::getContent);
    }

    public long getStudentCount() {
        final ResponseEntity<PagedResources<Student>> studentResponse = template
                .exchange("http://students-service/students?page=1&size=1",
                        HttpMethod.GET, null,
                        new TypeReferences.PagedResourcesType<Student>());


        return studentResponse.getBody().getMetadata().getTotalElements();
    }
}
