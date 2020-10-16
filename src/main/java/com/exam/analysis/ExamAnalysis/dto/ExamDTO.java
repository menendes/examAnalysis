package com.exam.analysis.ExamAnalysis.dto;

import com.exam.analysis.ExamAnalysis.enums.Lesson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExamDTO {
    private int examCode;
    private String examPlace;
    private LocalDateTime examDate;
    private int examMinute;
    private double grade;
    private Lesson lesson;

}
