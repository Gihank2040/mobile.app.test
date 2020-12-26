package com.example.autowired.test.autow22.controller;

import com.example.autowired.test.autow22.dto.UserDto;
import com.example.autowired.test.autow22.model.UserDetailModel;
import com.example.autowired.test.autow22.responce.UserResponce;
import com.example.autowired.test.autow22.service.StudentImpl;
import com.example.autowired.test.autow22.service.UserImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class Cont {

    @Autowired
    StudentImpl studentImpl;

    @Autowired
    UserImpl userImpl;

    @PostMapping
    public UserResponce createUser(@RequestBody UserDetailModel userDetail) {
        UserResponce returnValue = new UserResponce();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetail, userDto);
        UserDto createUser = userImpl.createUser(userDto);
        BeanUtils.copyProperties(createUser, returnValue);
        return returnValue;
    }

    @GetMapping(value = "student")
    public String getUser() {
        return studentImpl.test();
    }

}
