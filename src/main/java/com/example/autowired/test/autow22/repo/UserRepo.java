package com.example.autowired.test.autow22.repo;

import com.example.autowired.test.autow22.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);
    UserEntity findUserByUserId(String userId);
}
