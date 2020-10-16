package com.exam.analysis.ExamAnalysis.serviceImpl;

import com.exam.analysis.ExamAnalysis.dto.StudentDTO;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.model.StudentExam;

import java.util.List;
import java.util.Map;


public interface StudentImpl extends Calculate{
    public abstract Student createStudent(Student student);
    public abstract Map<String,Object> getStudentDetails(int id);
    public abstract List<StudentDTO> getAllStudents(int number);
}
