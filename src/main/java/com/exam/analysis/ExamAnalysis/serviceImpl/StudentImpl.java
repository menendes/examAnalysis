package com.exam.analysis.ExamAnalysis.serviceImpl;

import com.exam.analysis.ExamAnalysis.model.Student;

import java.util.List;


public interface StudentImpl {
    public abstract Student createStudent(Student student);
    public abstract Student getStudentDetails(int id);
    public abstract List<Student> getAllStudents();
}
