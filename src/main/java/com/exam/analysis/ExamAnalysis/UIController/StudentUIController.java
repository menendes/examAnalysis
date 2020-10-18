package com.exam.analysis.ExamAnalysis.UIController;

import com.exam.analysis.ExamAnalysis.dto.ExamDTO;
import com.exam.analysis.ExamAnalysis.dto.StudentDTO;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.service.StudentExamService;
import com.exam.analysis.ExamAnalysis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentUIController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentExamService studentExamService;

    public Object[][] getStudents(){
        List<StudentDTO> students = studentService.getAllStudents(0);
        Object [][] rows = new Object[students.size()][4];
        int length = rows.length;
        for(int i = 0; i < length;i++){
            rows[i][0] = students.get(i).getName();
            rows[i][1] = students.get(i).getSurname();
            rows[i][2] = students.get(i).getStudentNumber();
            rows[i][3] = students.get(i).getAvgGrade();
        }
        return rows;
    }

    public Object[][] getStudentDetail(int studentId){

        Map<String,Object> studentDetailMap = studentService.getStudentDetails(studentId);
        Student fetchStudent = (Student)studentDetailMap.get("studentInfo");

        Object [][] rows = new Object[8][2];
        rows[0][0] = "Ad";
        rows[1][0] = "Soyad";
        rows[2][0] = "Öğrenci No";
        rows[3][0] = "Doğum Tarihi";
        rows[4][0] = "Adres";
        rows[5][0] = "Cinsiyet";
        rows[6][0] = "Sınıf";
        rows[7][0] = "Kayıt Tarihi";

        rows[0][1] = fetchStudent.getName();
        rows[1][1] = fetchStudent.getSurname();
        rows[2][1] = fetchStudent.getStudentNumber();
        rows[3][1] = fetchStudent.getBornDate();
        rows[4][1] = fetchStudent.getAddress();
        rows[5][1] = fetchStudent.getGender();
        rows[6][1] = fetchStudent.getClassroom();
        rows[7][1] = fetchStudent.getRegistrationDate();

        return rows;
    }

    public Object[][] getStudentExamDetail(int studentId){

        Map<String,Object> studentDetailMap = studentService.getStudentDetails(studentId);
        List<ExamDTO> exams = (List<ExamDTO>)studentDetailMap.get("exams");
        Object [][] rows = new Object[exams.size()][6];
        int lentgh = rows.length;
        for(int i = 0; i<lentgh; i++){
            rows[i][0] = exams.get(i).getExamCode();
            rows[i][1] = exams.get(i).getLesson();
            rows[i][2] = exams.get(i).getExamPlace();
            rows[i][3] = exams.get(i).getExamDate();
            rows[i][4] = exams.get(i).getExamMinute();
            rows[i][5] = exams.get(i).getGrade();
        }

        return rows;
    }

    public Student addStudent(Student student){
        return studentService.createStudent(student);
    }

    public StudentExam assignExamToStudent(StudentExam assignedExam){
        return studentExamService.assignExam(assignedExam);
    }

    public List<Map<String,Object>> getStudentsForReports(){

        List<Student> studentList = studentService.getStudentsForReports();
        List<Map<String,Object>> studentReportPayload = new ArrayList<>();

        studentList.forEach(student -> {
            Map<String,Object> studentDetail =  studentService.getStudentDetails(student.getStudentNumber());
            studentReportPayload.add(studentDetail);
        });

        return studentReportPayload;
    }
}
