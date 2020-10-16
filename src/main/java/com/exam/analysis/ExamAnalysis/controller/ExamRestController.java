package com.exam.analysis.ExamAnalysis.controller;

import com.exam.analysis.ExamAnalysis.enums.CustomError;
import com.exam.analysis.ExamAnalysis.model.Exam;
import com.exam.analysis.ExamAnalysis.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/exam")
public class ExamRestController {

    @Autowired
    ExamService examService;

    @PostMapping("/addExam")
    public Exam addExam(@Valid @RequestBody Exam exam){
        Exam addedExam = examService.addExam(exam);
        return addedExam;
    }

    @GetMapping("/getDetails/{id}")
    public Map<String, Object> getDetails(@PathVariable int id){
        return examService.getExamDetail(id);
    }

    @GetMapping("/getAllExams")
    public List<Exam> getAllExams(){
        return examService.getExamList();
    }






    // Custom Error Valid
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<CustomError, Object> handelError(MethodArgumentNotValidException ex ) {
        Map<CustomError, Object> customErrorPayload = new LinkedHashMap<>();
        List<HashMap<String, Object>> errors = new ArrayList<>();
        List<ObjectError> getErrors = ex.getBindingResult().getAllErrors();

        for (ObjectError err : getErrors) {
            String fiedName = ( (FieldError) err ).getField();
            String message = err.getDefaultMessage();
            HashMap<String, Object> hmError = new LinkedHashMap<>();
            hmError.put("fiedName", fiedName);
            hmError.put("message", message);
            errors.add(hmError);
        }
        customErrorPayload.put(CustomError.errors, errors);
        return customErrorPayload;
    }
}
