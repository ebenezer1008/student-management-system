package com.student.management.controller;

import com.student.management.model.StudentModel;
import com.student.management.service.StudentService;
import com.student.management.structure.ResponseStructure;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @DeleteMapping("/delete-student/{studentId}")
    public ResponseEntity<ResponseStructure<StudentModel>> deleteStudent(@PathVariable @Positive(message = "Enter the valid number") Integer studentId){
        return studentService.deleteStudent(studentId);
    }

    @PutMapping("/update-student/{studentId}")
    public ResponseEntity<ResponseStructure<StudentModel>> updateStudent(@PathVariable @Positive(message = "Enter the valid number") Integer studentId
            ,@Valid @RequestBody StudentModel studentModel){
        return studentService.updateStudent(studentId,studentModel);
    }

    @PatchMapping("/change-course/{studentId}")
    public ResponseEntity<ResponseStructure<StudentModel>> changeCourse(@PathVariable @Positive(message = "Enter the valid number") Integer studentId,
                                                                        @RequestParam @Positive(message = "Enter the valid number") Integer courseId){
       return studentService.changeCourse(studentId,courseId);
    }

    @GetMapping("/fetch-student/{id}")
    public ResponseEntity<ResponseStructure<StudentModel>> getStudent(@PathVariable @Positive(message = "Enter the valid number") Integer id){
        return studentService.getStudent(id);
    }

    @PostMapping("/save-student")
    public ResponseEntity<ResponseStructure<StudentModel>> saveStudent(@Valid @RequestBody StudentModel studentObj,
                                                                       @RequestParam @Positive(message = "Enter the valid number") Integer courseId){
        return studentService.saveStudent(studentObj,courseId);
    }

}
