package com.exam.analysis.ExamAnalysis.service;

import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.repository.ExamRepository;
import com.exam.analysis.ExamAnalysis.serviceImpl.ExamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService implements ExamImpl {

    @Autowired
    ExamRepository examRepository;


    @Override
    public List<Exam> getExamList() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamDetail(int examCode) {
        return examRepository.findById(examCode);
    }

    @Override
    public Exam addExam(Exam exam) {
        return  examRepository.save(exam);
    }
}
