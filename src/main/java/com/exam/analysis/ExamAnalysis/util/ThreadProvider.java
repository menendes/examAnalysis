package com.exam.analysis.ExamAnalysis.util;

import com.exam.analysis.ExamAnalysis.dto.ExamDTO;
import com.exam.analysis.ExamAnalysis.dto.ReportDTO;
import com.exam.analysis.ExamAnalysis.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
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
        try{
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setStudent((Student)data.get("studentInfo"));
            reportDTO.setExamsListForRelatedUser((List<ExamDTO>)data.get("exams"));

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(reportDTO);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
