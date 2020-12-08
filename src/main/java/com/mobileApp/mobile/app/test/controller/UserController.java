package com.mobileApp.mobile.app.test.controller;

import com.mobileApp.mobile.app.test.dto.UserDto;
import com.mobileApp.mobile.app.test.model.Student;
import com.mobileApp.mobile.app.test.responce.UserResponce;
import com.mobileApp.mobile.app.test.model.UserDetailModel;
import com.mobileApp.mobile.app.test.service.StudentService;
import com.mobileApp.mobile.app.test.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

/*

    @PostMapping
    public UserResponce createUser(@RequestBody UserDetailModel userdetail){
        UserResponce returnValue = new UserResponce();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userdetail,userDto);
        UserDto createUser= userService.createUser(userDto);
        BeanUtils.copyProperties(createUser,returnValue);
        return returnValue;
    }
*/

@PostMapping(value = "tt")
    public Student creatStudent(@RequestBody Student student){
     return  studentService.createStudent(student);

    }


}
