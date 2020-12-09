package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.Utils.Utils;
import com.example.autowired.test.autow22.dto.UserDto;
import com.example.autowired.test.autow22.entity.Userentity;
import com.example.autowired.test.autow22.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    private Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        //check email address already exist using custom query
        //if(userRepo.findUserByEmail(userDto.getEmail()) != null) throw new RuntimeException("Record already exists");
        Userentity userEntity = new Userentity();
        BeanUtils.copyProperties(userDto, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(publicUserId);
        Userentity storeUser = userRepo.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storeUser, returnValue);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
