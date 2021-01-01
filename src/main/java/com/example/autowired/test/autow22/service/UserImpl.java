package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.Utils.Utils;
import com.example.autowired.test.autow22.dto.UserDto;
import com.example.autowired.test.autow22.entity.Userentity;
import com.example.autowired.test.autow22.repo.UserRepo;
import com.example.autowired.test.autow22.responce.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public UserDto getUserByEmail(String email) {
        Userentity userEntity = userRepo.findUserByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        Userentity userentity = userRepo.findUserByUserId(userId);
        if (userentity == null) throw new UsernameNotFoundException(userId);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userentity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserDto returnValue = new UserDto();
        Userentity userentity = userRepo.findUserByUserId(userId);
        if (userentity == null) throw new UsernameNotFoundException(ErrorMessages.UNABLE_TO_FIND_USER.getErrorMessage());
        userentity.setFirstName(userDto.getFirstName());
        userentity.setLastName(userDto.getLastName());
        Userentity updatedValue= userRepo.save(userentity);
        BeanUtils.copyProperties(updatedValue, returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        Userentity userentity = userRepo.findUserByUserId(userId);
        if (userentity == null) throw new UsernameNotFoundException(ErrorMessages.UNABLE_TO_FIND_USER.getErrorMessage());
        userRepo.delete(userentity);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Userentity userentity = userRepo.findUserByEmail(email);
        if (userentity == null) throw new UsernameNotFoundException(email);
        return new User(userentity.getEmail(), userentity.getEncryptedPassword(), new ArrayList<>());
    }
}
