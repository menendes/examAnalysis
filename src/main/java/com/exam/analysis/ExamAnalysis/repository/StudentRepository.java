package com.exam.analysis.ExamAnalysis.repository;

import com.exam.analysis.ExamAnalysis.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

  Student findById(int id);

}
