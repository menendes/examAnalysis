package com.exam.analysis.ExamAnalysis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamListDTO  extends ExamDTO{

    private int numberOfStudents;
    private double averageGrade;

    @JsonIgnore
    @Override
    public double getGrade() {
        return super.getGrade();
    }
}
