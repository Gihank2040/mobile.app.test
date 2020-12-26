package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.model.Student;
import com.example.autowired.test.autow22.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Override
    public Student studentService(Student student) {

        return studentRepo.save(student);
    }


    public List<Student> findStudent(Student student) {
        List<Student> xx = (List) studentRepo.findAll();

        return xx;

    }

    @Override
    public String test() {
        String xx = "this is test";
        return xx;
    }
}
