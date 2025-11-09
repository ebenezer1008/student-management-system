package com.student.management.dao;

import com.student.management.model.StudentModel;
import com.student.management.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentDao {
    @Autowired
    private StudentRepo studentRepo;

    public StudentModel saveStudent(StudentModel studentObj) {
        return studentRepo.save(studentObj);
    }

    public Optional<StudentModel> getStudentById(Integer id) {
        return studentRepo.findById(id);
    }

    public void deleteStudent(StudentModel studentModel) {
        studentRepo.delete(studentModel);
    }
}
