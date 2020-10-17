package com.exam.analysis.ExamAnalysis.view;

import javax.swing.*;

public class HomePage{

    public HomePage(){

        JFrame homeFrame = new JFrame("Exam Analysis");

        homeFrame.setSize(600, 600);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        homeFrame.add(panel);

        panel.setLayout(null);
        placeComponents(panel, homeFrame);
        homeFrame.setVisible(true);
    }

    static void placeComponents(JPanel panel, JFrame frame) {

        JButton studentButton = new JButton("Öğrenciler");
        studentButton.setBounds(0, 0, 100, 40);
        studentButton.addActionListener(e -> {
            frame.setVisible(false);
            StudentPage student = new StudentPage();
        });

        JButton examButton = new JButton("Sınavlar");
        examButton.setBounds(100, 0, 100, 40);
        examButton.addActionListener(e -> {
            frame.setVisible(false);
            ExamPage exam = new ExamPage();
        });

        JButton studentsExam = new JButton("Analiz");
        studentsExam.setBounds(200, 0, 100, 40);
        studentsExam.addActionListener(e -> {
            frame.setVisible(false);
            ExamAnalysisPage analysis = new ExamAnalysisPage();
        });

        JLabel welcomeText = new JLabel("Hoşgeldiniz... Başlamak için bir butona tıklayın.");
        welcomeText.setBounds(100, 100, 300, 200);

        panel.add(studentButton);
        panel.add(examButton);
        panel.add(studentsExam);
        panel.add(welcomeText);
    }
}