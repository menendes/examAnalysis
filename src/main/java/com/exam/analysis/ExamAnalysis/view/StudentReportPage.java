package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.StudentUIController;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import com.exam.analysis.ExamAnalysis.util.ThreadProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class StudentReportPage {

    @Autowired
    StudentUIController studentUIController;

    public StudentReportPage(){
        BeanProvider.autowire(this);
        init();
    }

    private void init(){
        JFrame studentReportFrame = new JFrame("Exam Analysis");

        studentReportFrame.setSize(1200, 800);
        studentReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        studentReportFrame.add(panel);

        //student page button
        JButton studentButton = new JButton("Öğrenciler");
        studentButton.setBounds(20, 10, 100, 40);
        studentButton.addActionListener(e -> {
            studentReportFrame.setVisible(false);
            StudentPage studentPage = new StudentPage();
        });

        //exam page button
        JButton examButton = new JButton("Sınavlar");
        examButton.setBounds(130, 10, 100, 40);
        examButton.addActionListener(e -> {
            studentReportFrame.setVisible(false);
            ExamPage examPage = new ExamPage();
        });

        JButton studentsExam = new JButton("Rapor Oluşturma");
        studentsExam.setBounds(240, 10, 120, 40);
        studentsExam.setEnabled(false);

        JButton createReport = new JButton("Raporları Oluştur");
        createReport.setBounds(300, 500, 200, 30);
        createReport.addActionListener(e -> {
            StringBuilder filePath = new StringBuilder("C:\\Users\\halil.koyuncu\\Desktop\\rapor");
            List<Map<String,Object>> students = studentUIController.getStudentsForReports();
            int length = students.size();

            ThreadProvider provider_1 = new ThreadProvider();
            ThreadProvider provider_2 = new ThreadProvider();
            ThreadProvider provider_3 = new ThreadProvider();
            ThreadProvider provider_4 = new ThreadProvider();
            ThreadProvider provider_5 = new ThreadProvider();

            for(int i = 0; i < length; i = i+5){
                try{
                    provider_1.setData(students.get(i));
                    provider_1.setFileName(filePath.append(i).append(".txt").toString());
                    provider_1.start();

                    provider_2.setData(students.get(i+1));
                    provider_2.setFileName(filePath.append(i+1).append(".txt").toString());
                    provider_2.start();

                    provider_3.setData(students.get(i+2));
                    provider_3.setFileName(filePath.append(i+2).append(".txt").toString());
                    provider_3.start();

                    provider_4.setData(students.get(i+3));
                    provider_4.setFileName(filePath.append(i+3).append(".txt").toString());
                    provider_4.start();

                    provider_5.setData(students.get(i+4));
                    provider_5.setFileName(filePath.append(i+4).append(".txt").toString());
                    provider_5.start();

                }catch (IndexOutOfBoundsException ex){
                    break;
                }
            }
        });

        panel.add(studentButton);
        panel.add(examButton);
        panel.add(studentsExam);
        panel.add(createReport);

        panel.setLayout(null);
        studentReportFrame.setVisible(true);
    }
}