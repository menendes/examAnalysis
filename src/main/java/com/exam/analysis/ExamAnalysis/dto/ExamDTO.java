package com.exam.analysis.ExamAnalysis.dto;

import com.exam.analysis.ExamAnalysis.enums.Lesson;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExamDTO implements Serializable {
    private int examCode;
    private String examPlace;
    private LocalDateTime examDate;
    private int examMinute;
    private double grade;
    private Lesson lesson;

}
