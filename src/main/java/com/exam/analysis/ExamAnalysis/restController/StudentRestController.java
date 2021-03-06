package com.exam.analysis.ExamAnalysis.restController;

import com.exam.analysis.ExamAnalysis.dto.StudentDTO;
import com.exam.analysis.ExamAnalysis.enums.CustomError;
import com.exam.analysis.ExamAnalysis.model.Student;
import com.exam.analysis.ExamAnalysis.model.StudentExam;
import com.exam.analysis.ExamAnalysis.service.StudentExamService;
import com.exam.analysis.ExamAnalysis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    public Map<String,Object> getDetails(@PathVariable int id){
        return studentService.getStudentDetails(id);
    }

    @GetMapping("/getAllStudents/{numberOfStudents}")
    @Cacheable("allExams")
    public List<StudentDTO> getAllStudents(@PathVariable int numberOfStudents){
        return studentService.getAllStudents(numberOfStudents);
    }

    @PostMapping("/assignExam")
    public StudentExam assignExam(@Valid @RequestBody StudentExam studentExam){
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
