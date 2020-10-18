package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.StudentUIController;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import com.exam.analysis.ExamAnalysis.util.ThreadProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;


@PropertySource("classpath:application.properties")
public class StudentReportPage {

    @Autowired
    StudentUIController studentUIController;

    @Value("${reportPath}")
    private String filePath;

    public StudentReportPage() {
        BeanProvider.autowire(this);
        init();
    }

    private void init() {
        JFrame studentReportFrame = new JFrame("Rapor Oluşturma");

        studentReportFrame.setSize(1200, 800);
        studentReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea jTextArea = new JTextArea();

        JButton createReport = new JButton("Raporları Oluştur");
        createReport.setBounds(300, 500, 200, 30);
        createReport.addActionListener(e -> {

            List<Map<String, Object>> students = studentUIController.getStudentsForReports();
            int length = students.size();

            ThreadProvider provider_1 = new ThreadProvider();
            ThreadProvider provider_2 = new ThreadProvider();
            ThreadProvider provider_3 = new ThreadProvider();
            ThreadProvider provider_4 = new ThreadProvider();
            ThreadProvider provider_5 = new ThreadProvider();

            Student student;

            for (int i = 0; i < length; i = i + 5) {
                try {

                    provider_1.setData(students.get(i));
                    provider_1.setFileRootPath(filePath);
                    provider_1.start();
                    student = (Student) students.get(i).get("studentInfo");
                    jTextArea.append(student.getName() + " " + student.getSurname() + " isimli öğrenci raporu üretildi" + "\n");

                    provider_2.setData(students.get(i + 1));
                    provider_2.setFileRootPath(filePath);
                    provider_2.start();
                    student = (Student) students.get(i + 1).get("studentInfo");
                    jTextArea.append(student.getName() + " " + student.getSurname() + " isimli öğrenci raporu üretildi" + "\n");

                    provider_3.setData(students.get(i + 2));
                    provider_3.setFileRootPath(filePath);
                    provider_3.start();
                    student = (Student) students.get(i + 2).get("studentInfo");
                    jTextArea.append(student.getName() + " " + student.getSurname() + " isimli öğrenci raporu üretildi" + "\n");

                    provider_4.setData(students.get(i + 3));
                    provider_4.setFileRootPath(filePath);
                    provider_4.start();
                    student = (Student) students.get(i + 3).get("studentInfo");
                    jTextArea.append(student.getName() + " " + student.getSurname() + " isimli öğrenci raporu üretildi" + "\n");

                    provider_5.setData(students.get(i + 4));
                    provider_5.setFileRootPath(filePath);
                    provider_5.start();
                    student = (Student) students.get(i + 4).get("studentInfo");
                    jTextArea.append(student.getName() + " " + student.getSurname() + " isimli öğrenci raporu üretildi" + "\n");

                } catch (IndexOutOfBoundsException ex) {
                    break;
                }
            }
        });

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jTextArea, BorderLayout.CENTER);

        JScrollPane jScrollPane = new JScrollPane(jPanel);
        studentReportFrame.add(createReport, BorderLayout.SOUTH);
        studentReportFrame.add(jScrollPane, BorderLayout.CENTER);
        studentReportFrame.setVisible(true);
    }
}