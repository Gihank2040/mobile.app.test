package com.example.autowired.test.autow22.controller;

import com.example.autowired.test.autow22.dto.AddressDTO;
import com.example.autowired.test.autow22.dto.UserDto;
import com.example.autowired.test.autow22.exceptions.UserServiceException;
import com.example.autowired.test.autow22.model.UserDetailModel;
import com.example.autowired.test.autow22.responce.*;
import com.example.autowired.test.autow22.service.AddressServiceImpl;
import com.example.autowired.test.autow22.service.StudentImpl;
import com.example.autowired.test.autow22.service.UserImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class UserController {
//App running with context path, It has property file

    @Autowired
    StudentImpl studentImpl;

    @Autowired
    UserImpl userImpl;

    @Autowired
    AddressServiceImpl addressService;

    //consumes ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    //this'll can post data which data types are xml and json both
    //in this method using customer error message, using UserServiceException, we can custom error msg representation
    @PostMapping(consumes ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponse createUser(@RequestBody UserDetailModel userDetail) throws Exception {
        if (userDetail.getEmail().isEmpty() || userDetail.getFirstName().isEmpty() || userDetail.getLastName().isEmpty() || userDetail.getPassword().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        //if (userDetail.getEmail().isEmpty()) throw new NullPointerException("This is trigger -> ApplicationExceptionHandler.handleOtherException");
        // Using user beanUtils to transfer object
        //UserDto userDto = new UserDto();
        //BeanUtils.copyProperties(userDetail, userDto);
        //Using Model mapper to transfer object. This is best way

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetail, UserDto.class);
        UserDto createUser = userImpl.createUser(userDto);
        UserResponse returnValue = modelMapper.map(createUser, UserResponse.class);
        //BeanUtils.copyProperties(createUser, returnValue);
        return returnValue;
    }

    //produces = MediaType.APPLICATION_XML_VALUE
    //It used to get response by default xml type
    //we can use one or more response types like below
    @GetMapping(path = "/{id}", produces ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUser(@PathVariable String id) {
        UserResponse returnValue = new UserResponse();
        UserDto getUser = userImpl.getUserByUserId(id);
        BeanUtils.copyProperties(getUser, returnValue);
        return returnValue;
    }

    @PutMapping(path = "/{id}", produces ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailModel userDetail) {
        UserResponse returnValue = new UserResponse();
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

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponse> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserResponse> returnValue = new ArrayList<>();
        List<UserDto> getUsers = userImpl.getUsers(page, limit);
        for (UserDto userDto : getUsers) {
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(userDto, response);
            returnValue.add(response);
        }
        return returnValue;

    }

    @GetMapping(path = "/{id}/addresses")
    public List<AddressResponse> getUserAddresses(@PathVariable String id) {

        List<AddressResponse> returnValue = new ArrayList<>();
        List<AddressDTO> addressDTO = addressService.getAddressesByUSer(id);

        if(addressDTO != null && !addressDTO.isEmpty()) {
            //java.lang.reflect.type
            //Match object to list using model mapper
            java.lang.reflect.Type listType = new TypeToken<List<AddressResponse>>() {
            }.getType();
            returnValue = new ModelMapper().map(addressDTO, listType);
        }

        return returnValue;
    }

}
