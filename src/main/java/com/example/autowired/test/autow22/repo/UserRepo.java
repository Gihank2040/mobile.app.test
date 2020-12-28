package com.example.autowired.test.autow22.repo;

import com.example.autowired.test.autow22.entity.Userentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Userentity, Long> {
    Userentity findUserByEmail(String email);

    Userentity findUserByUserId(String userId);
}
