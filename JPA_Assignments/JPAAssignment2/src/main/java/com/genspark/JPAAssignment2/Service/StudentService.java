package com.genspark.JPAAssignment2.Service;

import com.genspark.JPAAssignment2.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(int student_id);
    Student addStudent(Student student);
    Student updateStudent(Student student);
    String deleteStudentById(int student_id);
}
