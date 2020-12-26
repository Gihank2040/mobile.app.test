package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.model.Student;

import java.util.List;

public interface StudentService {
    public Student studentService(Student student);

    public List findStudent(Student student);

    String test();
}
