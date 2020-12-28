package com.example.autowired.test.autow22.controller;

import com.example.autowired.test.autow22.dto.UserDto;
import com.example.autowired.test.autow22.model.UserDetailModel;
import com.example.autowired.test.autow22.responce.UserResponce;
import com.example.autowired.test.autow22.service.StudentImpl;
import com.example.autowired.test.autow22.service.UserImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class Cont {

    @Autowired
    StudentImpl studentImpl;

    @Autowired
    UserImpl userImpl;

    //consumes ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    //this'll can post data which data types are xml and json both
    @PostMapping(consumes ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
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
    //produces = MediaType.APPLICATION_XML_VALUE
    //It used to get response by default xml type
    //we can use one or more response types like below
    @GetMapping(path = "/{id}", produces ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponce getUser(@PathVariable String id) {
        UserResponce returnValue = new UserResponce();
        UserDto getUser = userImpl.getUserByUserId(id);

        BeanUtils.copyProperties(getUser, returnValue);
        return returnValue;
    }

}
