package com.mobileApp.mobile.app.test.service;

import com.mobileApp.mobile.app.test.entity.UserEntity;
import com.mobileApp.mobile.app.test.dto.UserDto;
import com.mobileApp.mobile.app.test.service.UserService;
import com.mobileApp.mobile.app.test.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
/*    @Autowired
    UserRepo xxxxxxxx;*/

    @Override
    public UserDto createUser(UserDto user) {
      /* UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        userEntity.setEncryptedPassword("TestPass");
        userEntity.setUserId("userID");
       // UserEntity stortUserDetails= repo.save(userEntity);
        UserDto returnValue= new UserDto();
      //  BeanUtils.copyProperties(stortUserDetails,returnValue);*/
        return null;
    }
}
