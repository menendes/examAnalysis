package com.exam.analysis.ExamAnalysis.dto;

import com.exam.analysis.ExamAnalysis.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ReportDTO implements Serializable {
    private Student student;
    private List<ExamDTO> examsListForRelatedUser;
 }
