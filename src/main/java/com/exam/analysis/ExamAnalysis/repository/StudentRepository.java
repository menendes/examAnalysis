package com.exam.analysis.ExamAnalysis.repository;

import com.exam.analysis.ExamAnalysis.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

  Student findById(int id);
}
