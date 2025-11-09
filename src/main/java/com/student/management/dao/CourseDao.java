package com.student.management.dao;

import com.student.management.model.Course;
import com.student.management.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDao {

    @Autowired
    private CourseRepo courseRepo;

    public Course saveCourse(Course courseObj) {
       return courseRepo.save(courseObj);
    }

    public Optional<Course> findById(Integer courseId) {
        return courseRepo.findById(courseId);
    }

    public void deleteCourse(Course course) {
        courseRepo.delete(course);
    }


    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }
}
