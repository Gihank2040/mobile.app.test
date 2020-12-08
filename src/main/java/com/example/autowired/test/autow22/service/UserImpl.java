package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.dto.UserDto;
import com.example.autowired.test.autow22.entity.Userentity;
import com.example.autowired.test.autow22.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        Userentity userEntity = new Userentity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword("testPS");
        userEntity.setUserId("userID");
        Userentity storeUser = userRepo.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storeUser, returnValue);
        return returnValue;
    }
}
