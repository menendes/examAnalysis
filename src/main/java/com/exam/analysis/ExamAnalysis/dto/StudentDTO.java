package com.exam.analysis.ExamAnalysis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private int studentNumber;
    private String name;
    private String surname;
    private String classroom;
    private double avgGrade;
}
