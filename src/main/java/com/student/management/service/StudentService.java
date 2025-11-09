package com.student.management.service;

import com.student.management.dao.CourseDao;
import com.student.management.dao.StudentDao;
import com.student.management.exception.IdNotFoundException;
import com.student.management.model.Course;
import com.student.management.model.StudentModel;
import com.student.management.repo.CourseRepo;
import com.student.management.repo.StudentRepo;
import com.student.management.structure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;

    public ResponseEntity<ResponseStructure<StudentModel>> saveStudent(StudentModel studentObj, Integer courseId) {
            Course course = courseDao.findById(courseId)
                    .orElseThrow(() -> new IdNotFoundException("Course Id is not found"));

               studentObj.setCourse(course);
               StudentModel student=studentDao.saveStudent(studentObj);
               ResponseStructure<StudentModel> structure = new ResponseStructure<>();
               structure.setMessage("Student data has been saved");
               structure.setData(student);
               structure.setHttpStatus(HttpStatus.OK.value());

               return new ResponseEntity<ResponseStructure<StudentModel>>(structure,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<StudentModel>> getStudent(Integer id) {
        Optional<StudentModel> optStudent = studentDao.getStudentById(id);
        if(optStudent.isPresent()){
            ResponseStructure<StudentModel> structure = new ResponseStructure<>();
            structure.setMessage("Found");
            structure.setData(optStudent.get());
            structure.setHttpStatus(HttpStatus.FOUND.value());

            return new ResponseEntity<ResponseStructure<StudentModel>>(structure,HttpStatus.FOUND);
        }

        throw new IdNotFoundException("Student Id is nod found");
    }

    public ResponseEntity<ResponseStructure<StudentModel>> changeCourse(Integer studentId, Integer courseId) {
        StudentModel student = studentDao.getStudentById(studentId)
                .orElseThrow(() -> new IdNotFoundException("Student id is not present"));

        Course course = courseDao.findById(courseId).orElseThrow(() -> new IdNotFoundException("Course id is not found"));

                student.setCourse(course);
                StudentModel studentModel = studentDao.saveStudent(student);
                ResponseStructure<StudentModel> structure = new ResponseStructure<>();
                structure.setMessage("Successfully updated new course");
                structure.setData(studentModel);
                structure.setHttpStatus(HttpStatus.OK.value());
                return new ResponseEntity<ResponseStructure<StudentModel>>(structure,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<StudentModel>> updateStudent(Integer studentId, StudentModel studentModel) {
        Optional<StudentModel> existingStudent = studentDao.getStudentById(studentId);
        if(existingStudent.isPresent()){
            existingStudent.get().setStudentName(studentModel.getStudentName());
            StudentModel updatedStudent = studentDao.saveStudent(existingStudent.get());
            ResponseStructure<StudentModel> structure = new ResponseStructure<>();
            structure.setMessage("Updated");
            structure.setData(updatedStudent);
            structure.setHttpStatus(HttpStatus.OK.value());
            return new ResponseEntity<>(structure,HttpStatus.OK);
        }

        throw new IdNotFoundException("Id is not present");
    }

    public ResponseEntity<ResponseStructure<StudentModel>> deleteStudent(Integer studentId) {
        Optional<StudentModel> studentModel = studentDao.getStudentById(studentId);
        if(studentModel.isPresent()){
            studentDao.deleteStudent(studentModel.get());
            ResponseStructure<StudentModel> structure = new ResponseStructure<>();
            structure.setMessage("Successful");
            structure.setData(studentModel.get().getStudentName() + " has been removed from the institute");
            structure.setHttpStatus(HttpStatus.OK.value());
            return new ResponseEntity<>(structure,HttpStatus.OK);
        }

        throw new IdNotFoundException("Unable to delete, student is not present");
    }
}
