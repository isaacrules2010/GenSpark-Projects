package com.genspark.JPAAssignment2.Service;

import com.genspark.JPAAssignment2.Dao.StudentDao;
import com.genspark.JPAAssignment2.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements StudentService{

    @Autowired
    private StudentDao students;

    @Override
    public List<Student> getAllStudents() {
        return this.students.findAll();
    }

    @Override
    public Student getStudentById(int student_id) {
        Optional<Student> s = this.students.findById(student_id);
        Student student = null;
        if(s.isPresent()){
            student = s.get();
        }
        else{
            throw new RuntimeException("Student not found with id :: "+student_id);
        }
        return student;
    }

    @Override
    public Student addStudent(Student student) {
        return this.students.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return this.students.save(student);
    }

    @Override
    public String deleteStudentById(int student_id) {
        this.students.deleteById(student_id);
        return "Deleted Student Successfully";
    }
}
