package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.ExamUIController;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
public class ExamPage {

    @Autowired
    ExamUIController examUIController;

    public ExamPage() {
        BeanProvider.autowire(this);

        JFrame examFrame = new JFrame("Exam Analysis");
        examFrame.setSize(1200, 800);
        examFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        examFrame.add(panel);

        panel.setLayout(null);
        placeComponents(panel, examFrame);
        examFrame.setVisible(true);
    }

     void placeComponents(JPanel panel, JFrame frame) {

        //student page button
        JButton studentButton = new JButton("Öğrenciler");
        studentButton.setBounds(20, 10, 100, 40);
        studentButton.addActionListener(e -> {
            frame.setVisible(false);
            StudentPage student = new StudentPage();
        });

        //exam page button
        JButton examButton = new JButton("Sınavlar");
        examButton.setBounds(130, 10, 100, 40);
        examButton.setEnabled(false);

        //analysis page button
        JButton studentsExam = new JButton("Analiz");
        studentsExam.setBounds(240, 10, 100, 40);
        studentsExam.addActionListener(e -> {
            frame.setVisible(false);
            ExamAnalysisPage analysis = new ExamAnalysisPage();
        });

        String[] examTableColumn = {"Sınav Kodu", "Sınav Adı", "Sınav Tarihi", "Sınav Yeri","Süre","Öğrenci Sayısı","Ortalama"};
        JTable examTable = new JTable(examUIController.getExams(), examTableColumn);
        examTable.setBounds(30, 60, 1100, 500);

        JButton addExamButton = new JButton("Sınav Ekle");
        addExamButton.setBounds(10, 580, 200, 40);
        addExamButton.addActionListener(e -> {
                AddExamPage addExamPage = new AddExamPage();
        });

        panel.add(studentButton);
        panel.add(examButton);
        panel.add(studentsExam);
        panel.add(examTable);
        panel.add(addExamButton);
    }
}