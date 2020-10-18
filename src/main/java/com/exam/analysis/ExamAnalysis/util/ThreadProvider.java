package com.exam.analysis.ExamAnalysis.util;

import com.exam.analysis.ExamAnalysis.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Map;

public class ThreadProvider extends Thread {

    @Getter
    @Setter
    private Map<String,Object> data;

    @Getter
    @Setter
    private String fileRootPath;

    @Override
    public void run() {
        try {
            Student student = (Student)data.get("studentInfo");
            StringBuilder fullFilePath = new StringBuilder(fileRootPath);
            fullFilePath.append(student.getName()).append(student.getSurname()).append(".json");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(fullFilePath.toString()), data);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
