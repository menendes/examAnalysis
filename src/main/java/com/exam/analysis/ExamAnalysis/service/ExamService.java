package com.exam.analysis.ExamAnalysis.service;

import com.exam.analysis.ExamAnalysis.dto.ExamDetailStudent;
import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.repository.ExamRepository;
import com.exam.analysis.ExamAnalysis.repository.StudentExamRepository;
import com.exam.analysis.ExamAnalysis.serviceImpl.ExamImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamService implements ExamImpl {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    StudentExamRepository studentExamRepository;


    @Override
    public List<Exam> getExamList() {
        return examRepository.findAll();
    }

    @Override
    public Map<String,Object> getExamDetail(int examCode) {

        ModelMapper modelMapper = new ModelMapper();
        Map<String,Object> examDetail = new LinkedHashMap<>();

        List<StudentExam> studentExamList = studentExamRepository.findStudentExamByExam_ExamCode(examCode);
        List<ExamDetailStudent> studentList = new ArrayList<>();

        studentExamList.forEach(item ->{
            ExamDetailStudent student = modelMapper.map(item.getStudent(),ExamDetailStudent.class);
            student.setGrade(item.getGrade());
            studentList.add(student);
        });

        examDetail.put("examInformation",examRepository.findById(examCode));
        examDetail.put("students",studentList);

        return examDetail;
    }

    @Override
    public Exam addExam(Exam exam) {
        return  examRepository.save(exam);
    }
}
