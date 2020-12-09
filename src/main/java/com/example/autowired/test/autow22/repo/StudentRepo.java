package com.example.autowired.test.autow22.repo;

import com.example.autowired.test.autow22.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<Student, Integer> {
}
