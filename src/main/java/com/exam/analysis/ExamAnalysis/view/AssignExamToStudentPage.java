package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.StudentUIController;
import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AssignExamToStudentPage {

    @Autowired
    StudentUIController studentUIController;

    public AssignExamToStudentPage() {
        BeanProvider.autowire(this);
        init();
    }

    private void init() {
        JFrame sExamFormFrame = new JFrame();
        sExamFormFrame.setSize(600, 600);
        sExamFormFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JTextField studentID, examCode, grade;
        JButton registerButton = new JButton("Kayıt");
        JButton fileChooser = new JButton("Dosyadan Seç");

        studentID = new JTextField("Öğrenci Numarası");
        studentID.setBounds(50, 110, 500, 30);

        examCode = new JTextField("Sınav Kodu");
        examCode.setBounds(50, 160, 500, 30);

        grade = new JTextField("Not");
        grade.setBounds(50, 210, 500, 30);

        registerButton.setBounds(50, 400, 200, 30);
        registerButton.addActionListener(e -> {
            StudentExam assignedExam = new StudentExam();
            Student student = new Student();
            Exam exam = new Exam();
            student.setStudentNumber(Integer.parseInt(studentID.getText()));
            exam.setExamCode(Integer.parseInt(examCode.getText()));
            assignedExam.setExam(exam);
            assignedExam.setStudent(student);
            assignedExam.setGrade(Double.parseDouble(grade.getText()));
            studentUIController.assignExamToStudent(assignedExam);
            sExamFormFrame.setVisible(false);
        });

        //read file and insert data to database
        fileChooser.setBounds(300, 400, 200, 30);
        fileChooser.addActionListener(e -> {
            List<StudentExam> studentGrades = new ArrayList<>();
            final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                try {
                    BufferedReader in;
                    in = new BufferedReader(new FileReader(selectedFile));
                    String line = in.readLine();
                    while (line != null) {

                        //create studentExam payload
                        StudentExam studentExam = new StudentExam();
                        Student student = new Student();
                        Exam exam = new Exam();

                        //split the string
                        String[] getData = line.split(" ");

                        //set the data
                        student.setStudentNumber(Integer.parseInt(getData[0]));
                        exam.setExamCode(Integer.parseInt(getData[1]));
                        studentExam.setStudent(student);
                        studentExam.setExam(exam);
                        studentExam.setGrade(Double.parseDouble(getData[2]));

                        //add the list
                        studentGrades.add(studentExam);

                        //pass the next line
                        line = in.readLine();
                    }
                    studentGrades.forEach(item -> {
                        studentUIController.assignExamToStudent(item);
                    });
                    sExamFormFrame.setVisible(false);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        sExamFormFrame.add(studentID);
        sExamFormFrame.add(examCode);
        sExamFormFrame.add(grade);
        sExamFormFrame.add(registerButton);
        sExamFormFrame.add(fileChooser);
        sExamFormFrame.setLayout(null);
        sExamFormFrame.setVisible(true);
    }
}
