package com.exam.analysis.ExamAnalysis.serviceImpl;

import com.exam.analysis.ExamAnalysis.model.Exam;

import java.util.List;

public interface ExamImpl {

    public abstract List<Exam> getExamList();
    public abstract Exam getExamDetail(int examCode);
    public abstract Exam addExam(Exam exam);
}
