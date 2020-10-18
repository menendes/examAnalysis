package com.exam.analysis.ExamAnalysis.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int classroomId;

    @NotNull(message = "Classrom Name Can Not Be Null")
    @NotEmpty(message = "Classroom Name Can Not Be Empty")
    @Length(min = 2, max = 20, message = "Min 2, Max 20 Char Length")
    String classroomName;

    @Range(min = 10, max = 30, message = "Classroom capacity must be [10,30]")
    private int capacity;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="classroomId")
    private List<ExamClassroom> examClassrooms;

}
