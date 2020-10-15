package com.exam.analysis.ExamAnalysis.controller;

import com.exam.analysis.ExamAnalysis.enums.CustomError;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.service.StudentExamService;
import com.exam.analysis.ExamAnalysis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentExamService studentExamService;

    @PostMapping("/addStudent")
    public Student addStudent(@Valid @RequestBody Student student ){
        student.setRegistrationDate(LocalDate.now());
        Student createdStudent = studentService.createStudent(student);
        return createdStudent;
    }

    @GetMapping("/getDetails/{id}")
    public Student getDetails(@PathVariable int id){
        return studentService.getStudentDetails(id);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
       // Pageable limit = PageRequest.of(1,2);
        return studentService.getAllStudents();
    }

    @PostMapping("/assignExam")
    public StudentExam assignExam(@Valid @RequestBody StudentExam studentExam){
        System.out.println("gradeeeeeee"+studentExam.getGrade());
        return studentExamService.assignExam(studentExam);
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
