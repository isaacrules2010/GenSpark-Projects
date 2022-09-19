package org.genspark;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("StudentConfig.xml");
        Student s = (Student)context.getBean(Student.class);
        s.info();
    }
}
