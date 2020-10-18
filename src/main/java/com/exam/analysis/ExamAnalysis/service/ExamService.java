package com.exam.analysis.ExamAnalysis.service;

import com.exam.analysis.ExamAnalysis.dto.ExamDetailStudent;
import com.exam.analysis.ExamAnalysis.dto.ExamListDTO;
import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.repository.ExamRepository;
import com.exam.analysis.ExamAnalysis.repository.StudentExamRepository;
import com.exam.analysis.ExamAnalysis.serviceImpl.ExamImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@PropertySource("classpath:application.properties")
public class ExamService implements ExamImpl {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    StudentExamRepository studentExamRepository;

    @Value("${examTablePerPageCount}")
    int  numberOfExamPerPage;

    @Override
    public List<ExamListDTO> getExamList(int pageIndex) {

        ModelMapper modelMapper = new ModelMapper();

        List<ExamListDTO> allExams = new ArrayList<>();
        Pageable limit = PageRequest.of(0,numberOfExamPerPage);
        Page<Exam> exams = examRepository.findAll(limit);

        exams.forEach(exam -> {
                ExamListDTO examListDTO = modelMapper.map(exam,ExamListDTO.class);
                double avg = calculateAverage(exam.getExamCode());
                examListDTO.setAverageGrade(avg);
                examListDTO.setNumberOfStudents(studentExamRepository.findStudentExamByExam_ExamCode(exam.getExamCode()).size());
                allExams.add(examListDTO);
        });

        return allExams;
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

    @Override
    public double calculateAverage(int id) {
        double sumOfGrades = 0;
        List<StudentExam> studentExamList = studentExamRepository.findStudentExamByExam_ExamCode(id);
        int numberOfStudents = studentExamList.size();

        Iterator<StudentExam> iterator = studentExamList.iterator();

        while(iterator.hasNext()){
            sumOfGrades += iterator.next().getGrade();
        }
        return sumOfGrades/numberOfStudents;
    }
}
