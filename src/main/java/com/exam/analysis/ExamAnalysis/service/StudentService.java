package com.exam.analysis.ExamAnalysis.service;

import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.repository.StudentExamRepository;
import com.exam.analysis.ExamAnalysis.repository.StudentRepository;
import com.exam.analysis.ExamAnalysis.serviceImpl.StudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentImpl {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentExamRepository studentExamRepository;

    @Override
    public Student createStudent(Student student) {
      return  studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getStudentDetails(int id) {
        return  studentRepository.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
