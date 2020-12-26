package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public UserDto createUser(UserDto userDto);
    UserDto getUserByEmail(String email);

}
