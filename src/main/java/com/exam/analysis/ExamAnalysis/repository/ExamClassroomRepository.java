package com.exam.analysis.ExamAnalysis.repository;

import com.exam.analysis.ExamAnalysis.model.Classroom;
import com.exam.analysis.ExamAnalysis.model.ExamClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamClassroomRepository extends JpaRepository<ExamClassroom,Integer> {
}
