package org.vaadin.risto.microservices.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.risto.microservices.rest.student.Student;
import org.vaadin.risto.microservices.rest.student.StudentsClient;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.data.BackEndDataSource;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.NumberRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme(ValoTheme.THEME_NAME)
public class MicroserviceUI extends UI {

    @Autowired
    private StudentsClient studentsClient;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Grid<Student> studentGrid = new Grid<>();
        studentGrid.addColumn("Name", Student::getName);
        studentGrid.addColumn("Age", Student::getAge, new NumberRenderer());

        studentGrid.setDataSource(new BackEndDataSource<>(
                query -> studentsClient
                        .getStudents(query.getOffset(), query.getLimit()),
                query -> (int) studentsClient.getStudentCount()));

        setContent(new MVerticalLayout(studentGrid).withFullWidth()
                .withMargin(true)
                .withAlign(studentGrid, Alignment.MIDDLE_CENTER));
    }
}
