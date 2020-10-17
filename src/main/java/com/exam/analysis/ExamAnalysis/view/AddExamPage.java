package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.ExamUIController;
import com.exam.analysis.ExamAnalysis.enums.Lesson;
import com.exam.analysis.ExamAnalysis.model.Classroom;
import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.model.ExamClassroom;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddExamPage {

    @Autowired
    ExamUIController examUIController;

    public AddExamPage(){
        BeanProvider.autowire(this);
        init();
    }

    private void init(){
        JFrame formFrame = new JFrame();
        formFrame.setSize(600, 600);
        formFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        String[] lessons = {"TURKISH","MATH","PHYSIC","BIOLOGY","CHEMISTRY","HISTORY"};
        JComboBox lessonsComboBox = new JComboBox(lessons);
        lessonsComboBox.setBounds(50, 10, 500, 30);

        JTextField  examDate, examPlace, examTime;

        examPlace = new JTextField("Sınav Yeri");
        examPlace.setBounds(50, 60, 500, 30);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        examDate = new JTextField("Sınav Tarihi ve Saati");
        examDate.setBounds(50, 110, 500, 30);

        examTime = new JTextField("Sınav Süresi");
        examTime.setBounds(50, 160, 500, 30);

        List<Classroom> classrooms = examUIController.getClassrooms();
        List<String> choosedClassrooms = new ArrayList<>();

        classrooms.forEach(classroom -> {
            choosedClassrooms.add(classroom.getClassroomName());
        });
        JComboBox classroomComboBox = new JComboBox(choosedClassrooms.toArray());
        classroomComboBox.setBounds(50,210,500,30);

        JButton registerButton = new JButton("Kayıt");
        registerButton.setBounds(250, 400, 100, 30);
        registerButton.addActionListener(e -> {
            Exam exam = new Exam();
            String selectedLesson = lessonsComboBox.getItemAt(lessonsComboBox.getSelectedIndex()).toString();
            switch (selectedLesson){
                case "TURKISH" :  exam.setLesson(Lesson.TURKISH);
                    break;
                case "MATH": exam.setLesson(Lesson.MATH);
                    break;
                case "PHYSIC": exam.setLesson(Lesson.PHYSIC);
                    break;
                case "BIOLOGY": exam.setLesson(Lesson.BIOLOGY);
                    break;
                case "CHEMISTRY": exam.setLesson(Lesson.CHEMISTRY);
                    break;
                case "HISTORY": exam.setLesson(Lesson.HISTORY);
                    break;
            }
            exam.setExamPlace(examPlace.getText());
            exam.setExamDate(LocalDateTime.parse(examDate.getText().trim(),df));
            exam.setExamMinute(Integer.parseInt(examTime.getText()));

            ExamClassroom classroom = new ExamClassroom();
            classroom.setClassroomId(1);
            List<ExamClassroom> classroomList = new ArrayList<>();
            classroomList.add(classroom);
            exam.setExamClassrooms(classroomList);

            examUIController.addExam(exam);
            formFrame.setVisible(false);
        });

        formFrame.add(lessonsComboBox);
        formFrame.add(examPlace);
        formFrame.add(examDate);
        formFrame.add(examTime);
        formFrame.add(classroomComboBox);
        formFrame.add(registerButton);

        formFrame.setLayout(null);
        formFrame.setVisible(true);
    }
}
