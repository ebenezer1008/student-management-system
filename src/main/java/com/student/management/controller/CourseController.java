package com.student.management.controller;

import com.student.management.model.Course;
import com.student.management.service.CourseService;
import com.student.management.service.StudentService;
import com.student.management.structure.ResponseStructure;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/save-course")
    public ResponseEntity<ResponseStructure<Course>> saveCourse(@Valid @RequestBody Course courseObj){
            return courseService.saveCourse(courseObj);
    }

    @DeleteMapping("/delete-course/{courseId}")
    public ResponseEntity<ResponseStructure<Course>> deleteCourse(@PathVariable @Positive(message = "Enter the valid number") Integer courseId){
        return courseService.deleteCourse(courseId);
    }

    @GetMapping("/get-all-course")
    public ResponseEntity<ResponseStructure<List<Course>>> getAllCourse(){
        return courseService.getAllCourse();
    }
}
