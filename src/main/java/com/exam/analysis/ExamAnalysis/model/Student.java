package com.exam.analysis.ExamAnalysis.model;


import com.exam.analysis.ExamAnalysis.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity(name = "student")
@Data
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentNumber;

    @NotNull(message = "Name Can Be Not Null")
    @NotEmpty(message = "Name Can Be Not Empty")
    @Length(min = 3, max = 30, message = "Min 3, Max 30 Char Length")
    private String name;

    @NotNull(message = "Surname Can Not Be Null")
    @NotEmpty(message = "Surname Can Not Be Empty")
    @Length(min = 2, max = 30, message = "Min 2, Max 30 Char Length")
    private String surname;

    @NotNull(message = "Address Can Not Be Null")
    @NotEmpty(message = "Address Can Not Be Empty")
    @Length(min = 20, max = 80, message = "Min 20, Max 80 Char Length")
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Mail Not Null")
    @NotEmpty(message = "Name Can Not Empty")
    private String classroom;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate bornDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;


/*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentExam> exams;
*/
/*
    @OneToMany(targetEntity = Exam.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Exam> exams;*/
}
