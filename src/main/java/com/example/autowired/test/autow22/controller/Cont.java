package com.example.autowired.test.autow22.controller;

import com.example.autowired.test.autow22.dto.UserDto;
import com.example.autowired.test.autow22.exceptions.UserServiceException;
import com.example.autowired.test.autow22.model.UserDetailModel;
import com.example.autowired.test.autow22.responce.ErrorMessages;
import com.example.autowired.test.autow22.responce.OperationStatus;
import com.example.autowired.test.autow22.responce.RequestOperationStatus;
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
    //in this method using customer error message, using UserServiceException, we can custom error msg representation
    @PostMapping(consumes ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponce createUser(@RequestBody UserDetailModel userDetail) throws Exception{
        if (userDetail.getEmail().isEmpty() || userDetail.getFirstName().isEmpty() || userDetail.getLastName().isEmpty() || userDetail.getPassword().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        //if (userDetail.getEmail().isEmpty()) throw new NullPointerException("This is trigger -> ApplicationExceptionHandler.handleOtherException");
        UserResponce returnValue = new UserResponce();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetail, userDto);
        UserDto createUser = userImpl.createUser(userDto);
        BeanUtils.copyProperties(createUser, returnValue);
        return returnValue;
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

    @PutMapping(path = "/{id}", produces ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponce updateUser(@PathVariable String id, @RequestBody UserDetailModel userDetail ) {
        UserResponce returnValue = new UserResponce();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetail, userDto);
        UserDto updateUser = userImpl.updateUser(id,userDto);
        BeanUtils.copyProperties(updateUser, returnValue);
        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatus deleteUser(@PathVariable String id){
        OperationStatus returnValue = new OperationStatus();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        userImpl.deleteUser(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }
}
