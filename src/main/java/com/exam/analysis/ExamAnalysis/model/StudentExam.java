package com.exam.analysis.ExamAnalysis.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class StudentExam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "studentNumber")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "examCode")
    private Exam exam;

    @Range(min = 0, max = 100, message = "Grades must be [0,100]")
    private double grade;


}
