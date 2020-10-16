package com.exam.analysis.ExamAnalysis.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ExamClassroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int classroomId;

}
