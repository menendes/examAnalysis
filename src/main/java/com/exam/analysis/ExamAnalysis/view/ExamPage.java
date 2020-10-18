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
        init();
    }

    private void init() {
        JFrame examFrame = new JFrame("Exam Analysis");
        examFrame.setSize(1200, 800);
        examFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        examFrame.add(panel);

        panel.setLayout(null);

        //student page button
        JButton studentButton = new JButton("Öğrenciler");
        studentButton.setBounds(20, 10, 100, 40);
        studentButton.addActionListener(e -> {
            examFrame.setVisible(false);
            StudentPage student = new StudentPage();
        });

        //exam page button
        JButton examButton = new JButton("Sınavlar");
        examButton.setBounds(130, 10, 100, 40);
        examButton.setEnabled(false);

        //analysis page button
        JButton studentReports = new JButton("Rapor Oluştur");
        studentReports.setBounds(240, 10, 125, 40);
        studentReports.addActionListener(e -> {
            StudentReportPage studentReportPage = new StudentReportPage();
        });

        String[] examTableColumn = {"Sınav Kodu", "Sınav Adı", "Sınav Tarihi", "Sınav Yeri", "Süre", "Öğrenci Sayısı", "Ortalama"};
        JTable examTable = new JTable(examUIController.getExams(), examTableColumn);
        examTable.setBounds(30, 60, 1100, 500);

        JButton addExamButton = new JButton("Sınav Ekle");
        addExamButton.setBounds(10, 580, 200, 40);
        addExamButton.addActionListener(e -> {
            AddExamPage addExamPage = new AddExamPage();
        });

        JButton examDetailButton = new JButton("Sınav Detayı");
        examDetailButton.setBounds(220, 580, 150, 40);
        examDetailButton.addActionListener(e -> {
            int columnIndex = 0;
            int rowIndex = examTable.getSelectedRow();
            String selectedExamCode = examTable.getModel().getValueAt(rowIndex, columnIndex).toString();
            ExamDetailPage examDetailPage = new ExamDetailPage(Integer.parseInt(selectedExamCode));
        });

        panel.add(studentButton);
        panel.add(examButton);
        panel.add(studentReports);
        panel.add(examTable);
        panel.add(addExamButton);
        panel.add(examDetailButton);

        examFrame.setVisible(true);

    }
}