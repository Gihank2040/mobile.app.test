package com.mobileApp.mobile.app.test.ui.controller;

import com.mobileApp.mobile.app.test.model.responce.UserResponce;
import com.mobileApp.mobile.app.test.user.model.UserDetailModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping
    public UserResponce createUser(@RequestBody UserDetailModel userdetail){
        return null;
    }

}
