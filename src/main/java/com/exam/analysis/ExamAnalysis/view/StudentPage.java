package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.StudentUIController;
import com.exam.analysis.ExamAnalysis.enums.Gender;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.service.StudentService;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;


public class StudentPage{

    @Autowired
    StudentUIController studentUIController;

    @Autowired
    StudentService studentService;

    static Object[][] studentListString;

    public StudentPage() {

        BeanProvider.autowire(this);

        JFrame studentFrame = new JFrame("Exam Analysis");

        studentFrame.setSize(600, 600);
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        studentFrame.add(panel);

        panel.setLayout(null);

        studentListString = studentUIController.getStudents();

        placeComponents(panel, studentFrame);

        studentFrame.setVisible(true);


    }

      void  placeComponents(JPanel panel, JFrame frame) {

        JButton studentButton = new JButton("Öğrenciler");
        studentButton.setBounds(0, 0, 100, 40);
        studentButton.setEnabled(false);

        JButton examButton = new JButton("Sınavlar");
        examButton.setBounds(100, 0, 100, 40);
        examButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ExamPage exam = new ExamPage();
            }
        });

        JButton studentsExam = new JButton("Analiz");
        studentsExam.setBounds(200, 0, 100, 40);
        studentsExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ExamAnalysisPage analysis = new ExamAnalysisPage();
            }
        });



        String[] column = {"Ad", "Soyad", "Öğrenci No", "Ortalama"};
        JTable studentTable = new JTable(studentListString, column);
        studentTable.setBounds(30, 60, 500, 300);

        JButton addStudentButton = new JButton("Öğrenci Ekle");
        addStudentButton.setBounds(10, 400, 200, 40);
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame formFrame = new JFrame();
                formFrame.setSize(600, 600);
                formFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                JTextField studentName, studentSurname, studentID, address, studentClass, bornDate;
                JRadioButton male = new JRadioButton("Male");
                JRadioButton female = new JRadioButton("Female");
                JButton registerButton = new JButton("Kayıt");

                studentName = new JTextField("Öğrenci Adı");
                studentName.setBounds(50, 10, 500, 30);

                studentSurname = new JTextField("Öğrenci Soyadı");
                studentSurname.setBounds(50, 60, 500, 30);



                address = new JTextField("Adres");
                address.setBounds(50, 110, 500, 30);

                male.setBounds(50, 160, 100, 30);
                female.setBounds(150, 160, 100, 30);
                ButtonGroup gender = new ButtonGroup();
                gender.add(male);
                gender.add(female);

                studentClass = new JTextField("Sınıf");
                studentClass.setBounds(50, 210, 500, 30);

                bornDate = new JTextField("Kayıt Tarihi");
                bornDate.setBounds(50, 260, 500, 30);

                registerButton.setBounds(250, 320, 100, 30);
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Student newStudent = new Student();
                        newStudent.setName(studentName.getText());
                        newStudent.setSurname(studentSurname.getText());
                        newStudent.setAddress(address.getText());
                        //newStudent.setBornDate(LocalDate.parse(bornDate.getText()));
                        newStudent.setRegistrationDate(LocalDate.now());
                        newStudent.setClassroom(studentClass.getText());
                        if(male.isSelected()){
                            newStudent.setGender(Gender.male);
                        }else{
                            newStudent.setGender(Gender.female);
                        }
                        studentService.createStudent(newStudent);

                        formFrame.setVisible(false);
                    }
                });

                formFrame.add(studentName);
                formFrame.add(studentSurname);
                formFrame.add(address);
                formFrame.add(male);
                formFrame.add(female);
                formFrame.add(studentClass);
                formFrame.add(bornDate);
                formFrame.add(registerButton);
                formFrame.setLayout(null);
                formFrame.setVisible(true);
            }
        });

        JButton addStudentExam = new JButton("Öğrenci Sınav Ekleme");
        addStudentExam.setBounds(350, 400, 200, 40);
        addStudentExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sExamFormFrame.setVisible(false);
                    }
                });

                fileChooser.setBounds(300, 400, 200, 30);
                fileChooser.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                        int returnVal = fc.showOpenDialog(null);

                        if (returnVal == JFileChooser.APPROVE_OPTION){
                            File selectedFile = fc.getSelectedFile();
                            try {
                                BufferedReader in;
                                in = new BufferedReader(new FileReader(selectedFile));
                                String line = in.readLine();
                                while (line != null){
                                    studentID.setText(line);
                                    line = in.readLine();
                                    examCode.setText(line);
                                    line = in.readLine();
                                    grade.setText(line);
                                    line = in.readLine();
                                }
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
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
        });

        panel.add(studentButton);
        panel.add(examButton);
        panel.add(studentsExam);
        panel.add(studentTable);
        panel.add(addStudentButton);
        panel.add(addStudentExam);
    }
}