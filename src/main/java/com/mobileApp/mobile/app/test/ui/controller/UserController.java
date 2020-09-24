package com.mobileApp.mobile.app.test.ui.controller;

import com.mobileApp.mobile.app.test.ws.dto.UserDto;
import com.mobileApp.mobile.app.test.model.responce.UserResponce;
import com.mobileApp.mobile.app.test.user.model.UserDetailModel;
import com.mobileApp.mobile.app.test.ws.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserResponce createUser(@RequestBody UserDetailModel userdetail){
        UserResponce returnValue = new UserResponce();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userdetail,userDto);
        UserDto createUser= userService.createUser(userDto);
        BeanUtils.copyProperties(createUser,returnValue);
        return returnValue;
    }



}
