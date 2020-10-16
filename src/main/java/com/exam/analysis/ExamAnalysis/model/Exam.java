package com.exam.analysis.ExamAnalysis.model;


import com.exam.analysis.ExamAnalysis.enums.Lesson;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "exam")
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int examCode;

    @Enumerated(EnumType.STRING)
    private Lesson lesson;

    @NotNull(message = "Place Can Not Be Null")
    @NotEmpty(message = "Place Can Not Be Empty")
    @Length(min = 25, max = 80, message = "Min 25, Max 80 Char Length")
    private String examPlace;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime examDate;

    @Range(min = 30, max = 240, message = "Exam time can be minimum 30 minute , maximum 240 minute.")
    private int examMinute;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "examCode")
    private List<ExamClassroom> examClassrooms;

}
