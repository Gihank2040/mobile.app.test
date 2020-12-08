package com.mobileApp.mobile.app.test.responce;

import com.mobileApp.mobile.app.test.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
