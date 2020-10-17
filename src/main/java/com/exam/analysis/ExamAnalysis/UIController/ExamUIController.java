package com.exam.analysis.ExamAnalysis.UIController;

import com.exam.analysis.ExamAnalysis.dto.ExamListDTO;
import com.exam.analysis.ExamAnalysis.model.Classroom;
import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.service.ClassroomService;
import com.exam.analysis.ExamAnalysis.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

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

}
