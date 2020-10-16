package com.exam.analysis.ExamAnalysis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamDetailStudent extends StudentDTO{
    double grade;

    @JsonIgnore
    @Override
    public double getAvgGrade() {
        return super.getAvgGrade();
    }

    @JsonIgnore
    @Override
    public String getClassroom() {
        return super.getClassroom();
    }
}
