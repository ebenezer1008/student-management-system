package com.student.management.service;

import com.student.management.dao.CourseDao;
import com.student.management.exception.IdNotFoundException;
import com.student.management.exception.NoDataFoundException;
import com.student.management.model.Course;
import com.student.management.structure.ResponseStructure;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public ResponseEntity<ResponseStructure<Course>> saveCourse(Course courseObj) {
            Course savedCourse = courseDao.saveCourse(courseObj);
            ResponseStructure<Course> structure = new ResponseStructure<>();
            structure.setMessage("Course data saved successfully");
            structure.setData(savedCourse);
            structure.setHttpStatus(HttpStatus.OK.value());
            return  new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Course>> deleteCourse(Integer courseId) {
        Course course = courseDao.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException("Course id is not found"));

            courseDao.deleteCourse(course);
            ResponseStructure<Course> structure = new ResponseStructure<>();
            structure.setMessage("Deleted");
            structure.setData(course.getCourseName() + " and its related students has been removed from the institute");
            structure.setHttpStatus(HttpStatus.OK.value());

            return new ResponseEntity<>(structure,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Course>>> getAllCourse() {
        List<Course> course=courseDao.getAllCourse();
        if(course.isEmpty()){
            throw  new NoDataFoundException("No data");
        }
        ResponseStructure<List<Course>> structure = new ResponseStructure<>();
        structure.setMessage("Available courses");
        structure.setData(course);
        structure.setHttpStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(structure,HttpStatus.OK);
    }
}
