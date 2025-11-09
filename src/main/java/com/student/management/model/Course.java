package com.student.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "ID should be null")
    private Integer courseId;
    @NotBlank(message = "Course Name should not be blank")
    private String courseName;
    @NotBlank(message = "Enter the valid duration")
    private String duration;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<StudentModel> studentList;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getDuration() {
        return duration;
    }

    public List<StudentModel> getStudentList() {
        return studentList;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(duration, course.duration);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, duration);
    }
}
