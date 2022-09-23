package com.genspark.JPAAssignment2.Controller;

import com.genspark.JPAAssignment2.Entity.Student;
import com.genspark.JPAAssignment2.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private StudentService students;
    //Home
    @GetMapping("/")
    public String home(){
        return "<HTML><H1>STUDENT DATABASE</H1></HTML>\n";
    }

    //GET
    @GetMapping("/students")
    public List<Student> getStudents(){
        return this.students.getAllStudents();
    }
    //GET by id
    @GetMapping("/students/{student_id}")
    public Student getStudentById(@PathVariable String student_id){
        return this.students.getStudentById(Integer.parseInt(student_id));
    }
    //POST
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return this.students.addStudent(student);
    }

    //PUT
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
        return this.students.updateStudent(student);
    }

    //DELETE
    @DeleteMapping("/students/{student_id}.delete")
    public String deleteStudentById(@PathVariable String student_id){
        return this.students.deleteStudentById(Integer.parseInt(student_id));
    }
}
