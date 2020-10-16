package com.exam.analysis.ExamAnalysis.UIController;

import com.exam.analysis.ExamAnalysis.dto.StudentDTO;
import com.exam.analysis.ExamAnalysis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentUIController {

    @Autowired
    StudentService studentService;

    @Bean(name ="getStudents" )
    public Object[][] getStudents(){
        List<StudentDTO> students = studentService.getAllStudents(5);
        Object [][] stringArray1 = new Object[students.size()][4];

        for(int i = 0; i<stringArray1.length;i++){
            stringArray1[i][0] = students.get(i).getName();
            stringArray1[i][1] = students.get(i).getSurname();
            stringArray1[i][2] = students.get(i).getStudentNumber();
            stringArray1[i][3] = students.get(i).getAvgGrade();
        }
        return stringArray1;
    }


}
