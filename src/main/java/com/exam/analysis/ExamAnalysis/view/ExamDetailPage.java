package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.ExamUIController;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

public class ExamDetailPage {

    private int selectedExamCode;

    @Autowired
    ExamUIController examUIController;

    public ExamDetailPage(int examCode){
        BeanProvider.autowire(this);
        this.selectedExamCode = examCode;
        init();
    }

    private void init(){
        JFrame eDetailFrame = new JFrame();
        eDetailFrame.setSize(1200, 800);
        eDetailFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //create student information table
        String[] studentDetailTableColumn = {"Öğrenci Numarası", "Ad","Soyad","Not"};
        Object[][] studentDetailTableRow = examUIController.getStudentsRelatedExam(selectedExamCode);
        JTable studentDetailTable = new JTable(studentDetailTableRow,studentDetailTableColumn);
        studentDetailTable.setBounds(20,20,500,650);

        //create exam information table
        String[] examDetailTableColumn = {"Key", "Value"};
        Object[][] examDetailTableRow = examUIController.getExamDetail(selectedExamCode);
        JTable examDetailTable = new JTable(examDetailTableRow,examDetailTableColumn);
        examDetailTable.setBounds(550,20,500,100);

        eDetailFrame.add(studentDetailTable);
        eDetailFrame.add(examDetailTable);
        eDetailFrame.setLayout(null);
        eDetailFrame.setVisible(true);
    }
}
