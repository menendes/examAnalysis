package com.exam.analysis.ExamAnalysis.service;

import com.exam.analysis.ExamAnalysis.dto.ExamDTO;
import com.exam.analysis.ExamAnalysis.dto.StudentDTO;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.repository.StudentExamRepository;
import com.exam.analysis.ExamAnalysis.repository.StudentRepository;
import com.exam.analysis.ExamAnalysis.serviceImpl.StudentImpl;
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
public class StudentService implements StudentImpl {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentExamRepository studentExamRepository;

    @Value("${studentTablePerPageCount}")
    int  numberOfStudentPerPage;

    @Override
    public Student createStudent(Student student) {
      return  studentRepository.saveAndFlush(student);
    }

    @Override
    public Map<String,Object> getStudentDetails(int id) {
        Map<String,Object> studentDetails = new LinkedHashMap<>();
        List<StudentExam> studentExamList = studentExamRepository.findStudentExamByStudent_StudentNumber(id);
        List<ExamDTO> exams = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        studentExamList.forEach(item -> {
            ExamDTO examDTO = modelMapper.map(item,ExamDTO.class);
            examDTO.setGrade(item.getGrade());
            examDTO.setLesson(item.getExam().getLesson());
            exams.add(examDTO);
        });

        //put data into the map
        studentDetails.put("studentInfo",studentRepository.findById(id));
        studentDetails.put("exams",exams);
        return studentDetails;
    }

    @Override
    public List<StudentDTO> getAllStudents(int pageIndex) {

        //page index will set for pagenation process num it is dummy
        Pageable limit = PageRequest.of(0,numberOfStudentPerPage);
        ModelMapper modelMapper = new ModelMapper();
        List<StudentDTO> studentDTOS = new ArrayList<>();

        Page<Student> allStudents =  studentRepository.findAll(limit);
        allStudents.forEach(student -> {
                StudentDTO studentDTO = modelMapper.map(student,StudentDTO.class);
                double avg = calculateAverage(student.getStudentNumber());
                studentDTO.setAvgGrade(avg);
                studentDTOS.add(studentDTO);
        });
        return studentDTOS;
    }

    @Override
    public double calculateAverage(int studentNumber) {
        double sumOfGrades = 0;
        List<StudentExam> studentExamList = studentExamRepository.findStudentExamByStudent_StudentNumber(studentNumber);
        int numberOfExams = studentExamList.size();

        Iterator<StudentExam> iterator = studentExamList.iterator();

        while(iterator.hasNext()){
            sumOfGrades += iterator.next().getGrade();
        }

        return sumOfGrades/numberOfExams;
    }

    @Override
    public List<Student> getStudentsForReports() {
        return studentRepository.findAll();
    }
}
