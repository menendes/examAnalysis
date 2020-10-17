package com.exam.analysis.ExamAnalysis.service;

import com.exam.analysis.ExamAnalysis.model.Classroom;
import com.exam.analysis.ExamAnalysis.repository.ClassroomRepository;
import com.exam.analysis.ExamAnalysis.serviceImpl.ClassroomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService implements ClassroomImpl {

    @Autowired
    ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }
}
