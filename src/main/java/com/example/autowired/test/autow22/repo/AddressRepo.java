package com.example.autowired.test.autow22.repo;

import com.example.autowired.test.autow22.entity.AddressEntity;
import com.example.autowired.test.autow22.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends CrudRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserEntity(UserEntity userEntity);
}
