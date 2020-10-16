package com.exam.analysis.ExamAnalysis.repository;

import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam,Integer> {

   // List<StudentExam> findByStudentNumber(int id);

   // List<Exam> findStudentExamByStudent_StudentNumber();

    List<StudentExam> findStudentExamByStudent_StudentNumber(int id);
    List<StudentExam> findStudentExamByExam_ExamCode(int id);
}
