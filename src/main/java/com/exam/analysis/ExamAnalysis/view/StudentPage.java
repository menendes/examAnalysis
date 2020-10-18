package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.StudentUIController;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

public class StudentPage{

    @Autowired
    StudentUIController studentUIController;

    public StudentPage() {

        BeanProvider.autowire(this);

        JFrame studentFrame = new JFrame("Exam Analysis");
        studentFrame.setSize(1200, 800);
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        studentFrame.add(panel);

        panel.setLayout(null);
        placeComponents(panel, studentFrame);
        studentFrame.setVisible(true);
    }

      private void placeComponents(JPanel panel, JFrame frame) {

        //student page button
        JButton studentButton = new JButton("Öğrenciler");
        studentButton.setBounds(20, 10, 100, 40);
        studentButton.setEnabled(false);

        //exam page button
        JButton examButton = new JButton("Sınavlar");
        examButton.setBounds(130, 10, 100, 40);
        examButton.addActionListener(e -> {
            frame.setVisible(false);
            ExamPage exam = new ExamPage();
        });

        //analysis page button
        JButton studentReports = new JButton("Rapor Oluştur");
        studentReports.setBounds(240, 10, 100, 40);
        studentReports.addActionListener(e -> {
            frame.setVisible(false);
            StudentReportPage studentReportPage = new StudentReportPage();
        });

        //create student table
        String[] tableColumn = {"Ad", "Soyad", "Öğrenci No", "Ortalama"};
        JTable studentTable = new JTable(studentUIController.getStudents(), tableColumn);
        studentTable.setBounds(30, 60, 1100, 500);

        //trigger addStudent screen
        JButton addStudentButton = new JButton("Öğrenci Ekle");
        addStudentButton.setBounds(10, 580, 200, 40);
        addStudentButton.addActionListener(e -> {
                AddStudentPage addStudentPage = new AddStudentPage();
        });

        //trigger student detail screen
        JButton studentDetailButton = new JButton("Öğrenci Detayı");
        studentDetailButton.setBounds(220, 580, 150, 40);
        studentDetailButton.addActionListener(e -> {
            int columnIndex = 2;
            int rowIndex = studentTable.getSelectedRow();
            String selectedStudentId = studentTable.getModel().getValueAt(rowIndex, columnIndex).toString();
            StudentDetailPage studentDetailPage = new StudentDetailPage(Integer.parseInt(selectedStudentId));
        });

        //trigger assign exam to student screen
        JButton addStudentExam = new JButton("Öğrenci Sınav Ekleme");
        addStudentExam.setBounds(380, 580, 200, 40);
        addStudentExam.addActionListener(e -> {
                AssignExamToStudentPage assignExamToStudentPage = new AssignExamToStudentPage();
        });

        panel.add(studentButton);
        panel.add(examButton);
        panel.add(studentReports);
        panel.add(studentTable);
        panel.add(studentDetailButton);
        panel.add(addStudentButton);
        panel.add(addStudentExam);
    }
}