package com.exam.analysis.ExamAnalysis.UIController;

import com.exam.analysis.ExamAnalysis.dto.ExamDetailStudent;
import com.exam.analysis.ExamAnalysis.dto.ExamListDTO;
import com.exam.analysis.ExamAnalysis.model.Classroom;
import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.service.ClassroomService;
import com.exam.analysis.ExamAnalysis.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class ExamUIController {

    @Autowired
    ExamService examService;

    @Autowired
    ClassroomService classroomService;

    public Object[][] getExams(){

        List<ExamListDTO> exams = examService.getExamList();
        Object[][] rows = new Object[exams.size()][7];
        int length = rows.length;
        for(int i = 0; i < length; i++){
            rows[i][0] = exams.get(i).getExamCode();
            rows[i][1] = exams.get(i).getLesson();
            rows[i][2] = exams.get(i).getExamDate();
            rows[i][3] = exams.get(i).getExamPlace();
            rows[i][4] = exams.get(i).getExamMinute();
            rows[i][5] = exams.get(i).getNumberOfStudents();
            rows[i][6] = exams.get(i).getAverageGrade();
        }
        return rows;
    }

    public List<Classroom> getClassrooms(){
        return classroomService.getClassrooms();
    }

    public Exam addExam(Exam exam){
        return examService.addExam(exam);
    }

    public Object[][] getStudentsRelatedExam(int examCode){

        Map<String,Object> examDetailMap = examService.getExamDetail(examCode);
        List<ExamDetailStudent> students = (List<ExamDetailStudent>)examDetailMap.get("students");
        Object [][] rows = new Object[students.size()][4];
        int length = rows.length;
        for(int i = 0; i < length; i++){
            rows[i][0] = students.get(i).getStudentNumber();
            rows[i][1] = students.get(i).getName();
            rows[i][2] = students.get(i).getSurname();
            rows[i][3] = students.get(i).getGrade();
        }

        return rows;
    }

    public Object[][] getExamDetail(int examCode){
        Map<String,Object> examDetailMap = examService.getExamDetail(examCode);
        Exam fetchExam = (Exam) examDetailMap.get("examInformation");

        Object[][] rows = new Object[5][2];
        rows[0][0] = "Sınav Kodu";
        rows[1][0] = "Ders Adı";
        rows[2][0] = "Sınav Yeri";
        rows[3][0] = "Sınav Tarihi ve Saati";
        rows[4][0] = "Sınav Süresi";

        rows[0][1] = fetchExam.getExamCode();
        rows[1][1] = fetchExam.getLesson();
        rows[2][1] = fetchExam.getExamPlace();
        rows[3][1] = fetchExam.getExamDate();
        rows[4][1] = fetchExam.getExamMinute();

        return rows;
    }
}
