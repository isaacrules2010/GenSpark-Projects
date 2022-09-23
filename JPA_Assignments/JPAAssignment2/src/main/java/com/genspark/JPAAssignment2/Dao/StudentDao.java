package com.genspark.JPAAssignment2.Dao;

import com.genspark.JPAAssignment2.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer> {
}
