package com.exam.analysis.ExamAnalysis.util;

import com.exam.analysis.ExamAnalysis.dto.ExamDTO;
import com.exam.analysis.ExamAnalysis.dto.ReportDTO;
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
    private String fileName;

    @Override
    public void run() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(fileName), data);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
