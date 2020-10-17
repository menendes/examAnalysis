package com.exam.analysis.ExamAnalysis.view;

import com.exam.analysis.ExamAnalysis.UIController.StudentUIController;
import com.exam.analysis.ExamAnalysis.enums.Gender;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddStudentPage {

    @Autowired
    StudentUIController studentUIController;

    AddStudentPage(){
        BeanProvider.autowire(this);
        init();
    }

     private void init(){
         JFrame formFrame = new JFrame();
         formFrame.setSize(600, 600);
         formFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

         JTextField studentName, studentSurname, address, studentClass, bornDate;
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

         DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         bornDate = new JTextField("Doğum Tarihi");
         bornDate.setBounds(50, 260, 500, 30);

         //trigger add student service
         registerButton.setBounds(250, 320, 100, 30);
         registerButton.addActionListener(e1 -> {
             Student newStudent = new Student();
             newStudent.setName(studentName.getText());
             newStudent.setSurname(studentSurname.getText());
             newStudent.setAddress(address.getText());
             newStudent.setBornDate(LocalDate.parse(bornDate.getText().trim(),df));
             newStudent.setRegistrationDate(LocalDate.now());
             newStudent.setClassroom(studentClass.getText());
             if(male.isSelected()){
                 newStudent.setGender(Gender.male);
             }else{
                 newStudent.setGender(Gender.female);
             }
             studentUIController.addStudent(newStudent);
             formFrame.setVisible(false);
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
}
