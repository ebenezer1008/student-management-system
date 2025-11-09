package com.student.management.repo;

import com.student.management.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<StudentModel,Integer> {
}
