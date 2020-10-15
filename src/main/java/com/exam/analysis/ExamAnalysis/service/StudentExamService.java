package com.exam.analysis.ExamAnalysis.service;

import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.repository.StudentExamRepository;
import com.exam.analysis.ExamAnalysis.serviceImpl.StudentExamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentExamService implements StudentExamImpl {

    @Autowired
    StudentExamRepository studentExamRepository;

    @Override
    public StudentExam assignExam(StudentExam studentExam) {
        return studentExamRepository.saveAndFlush(studentExam);
    }
}
