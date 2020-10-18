package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.StudentUIController;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

public class StudentDetailPage {

    private int selectedStudentId;

    @Autowired
    StudentUIController studentUIController;

    public StudentDetailPage(int selectedStudentId) {
        BeanProvider.autowire(this);
        this.selectedStudentId = selectedStudentId;
        init();
    }

    private void init() {
        JFrame sDetailFrame = new JFrame();
        sDetailFrame.setSize(1200, 800);
        sDetailFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //create exams table
        String[] studentDetailExamTableColumn = {"Sınav Kodu", "Sınav Adı", "Sınav Yeri", "Sınav Tarihi", "Sınav Süresi", "Sınav Notu"};
        Object[][] studentDetailExamTableRow = studentUIController.getStudentExamDetail(selectedStudentId);
        JTable studentDetailExamTable = new JTable(studentDetailExamTableRow, studentDetailExamTableColumn);
        studentDetailExamTable.setBounds(20, 20, 800, 650);

        //create student information table
        String[] studentDetailTableColumn = {"Key", "Value"};
        Object[][] studentDetailTableRow = studentUIController.getStudentDetail(selectedStudentId);
        JTable studentDetailTable = new JTable(studentDetailTableRow, studentDetailTableColumn);
        studentDetailTable.setBounds(850, 20, 300, 130);

        //set table into the frame
        sDetailFrame.add(studentDetailTable);
        sDetailFrame.add(studentDetailExamTable);
        sDetailFrame.setLayout(null);
        sDetailFrame.setVisible(true);
    }
}
