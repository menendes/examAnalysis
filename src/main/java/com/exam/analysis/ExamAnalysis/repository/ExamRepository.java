package com.exam.analysis.ExamAnalysis.repository;

import com.exam.analysis.ExamAnalysis.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {

    Exam findById(int id);
}
