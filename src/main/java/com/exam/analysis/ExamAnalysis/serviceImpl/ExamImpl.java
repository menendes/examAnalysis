package com.exam.analysis.ExamAnalysis.serviceImpl;

import com.exam.analysis.ExamAnalysis.model.Exam;

import java.util.List;
import java.util.Map;

public interface ExamImpl {

    public abstract List<Exam> getExamList();
    public abstract Map<String,Object> getExamDetail(int examCode);
    public abstract Exam addExam(Exam exam);
}
