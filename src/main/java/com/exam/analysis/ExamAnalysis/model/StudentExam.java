package com.exam.analysis.ExamAnalysis.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class StudentExam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int studentNumber;

    private int examCode;

    @Range(min = 0, max = 100, message = "Grades must be [0,100]")
    private double grade;

    @ManyToOne(fetch = FetchType.EAGER)
    List<Student> students;

    @ManyToOne(fetch = FetchType.EAGER)
    List<Exam> exams;
}
