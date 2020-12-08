package com.mobileApp.mobile.app.test.service;

import com.mobileApp.mobile.app.test.model.Student;
import com.mobileApp.mobile.app.test.responce.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
      return studentRepository.save(student);
    }
}
