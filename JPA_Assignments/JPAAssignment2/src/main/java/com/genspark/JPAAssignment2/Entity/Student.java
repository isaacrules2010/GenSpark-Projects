package com.genspark.JPAAssignment2.Entity;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int student_id;
    private String name;
    private String advisor;
    private String degree;

    public Student(){
    }

    public Student(String name, String advisor, String degree) {
        this.name = name;
        this.advisor = advisor;
        this.degree = degree;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
