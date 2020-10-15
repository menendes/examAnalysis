package com.exam.analysis.ExamAnalysis.repository;

import com.exam.analysis.ExamAnalysis.model.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam,Integer> {

    List<StudentExam> findByStudentNumber(int id);
}
