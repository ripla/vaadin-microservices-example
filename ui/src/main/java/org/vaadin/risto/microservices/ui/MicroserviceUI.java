package org.vaadin.risto.microservices.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.risto.microservices.rest.student.StudentResource;
import org.vaadin.risto.microservices.rest.student.StudentsClient;
import org.vaadin.spring.annotation.VaadinUI;
import org.vaadin.viritin.layouts.MVerticalLayout;

@VaadinUI
public class MicroserviceUI extends UI {

    @Autowired
    private StudentsClient studentsClient;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        BeanItemContainer<StudentResource> studentsContainer = new BeanItemContainer<>(StudentResource.class, studentsClient.getStudents());
        studentsContainer.removeContainerProperty("links");

        Grid students = new Grid(studentsContainer);
        students.getColumn("id").setConverter(new HateoasLinkConverter());

        setContent(new MVerticalLayout(students).withFullWidth().withMargin(true).withAlign(students, Alignment.MIDDLE_CENTER));
    }
}
