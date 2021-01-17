package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.Utils.Utils;
import com.example.autowired.test.autow22.dto.AddressDTO;
import com.example.autowired.test.autow22.dto.UserDto;
import com.example.autowired.test.autow22.entity.UserEntity;
import com.example.autowired.test.autow22.repo.UserRepo;
import com.example.autowired.test.autow22.responce.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        ModelMapper modelMapper = new ModelMapper();

        for (int i = 0; i < userDto.getAddresses().size(); i++) {
            AddressDTO address = userDto.getAddresses().get(i);
            address.setUserDto(userDto);
            address.setAddressId(utils.generateAddressId(25));
            userDto.getAddresses().set(i, address);
        }
        // UserEntity userEntity = new UserEntity();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        //BeanUtils.copyProperties(userDto, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(publicUserId);

        UserEntity storeUser = userRepo.save(userEntity);
        // UserDto returnValue = new UserDto();
        UserDto returnValue = modelMapper.map(storeUser, UserDto.class);
        //BeanUtils.copyProperties(storeUser, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepo.findUserByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userentity = userRepo.findUserByUserId(userId);
        if (userentity == null) throw new UsernameNotFoundException(userId);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userentity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserDto returnValue = new UserDto();
        UserEntity userentity = userRepo.findUserByUserId(userId);
        if (userentity == null) throw new UsernameNotFoundException(ErrorMessages.UNABLE_TO_FIND_USER.getErrorMessage());
        userentity.setFirstName(userDto.getFirstName());
        userentity.setLastName(userDto.getLastName());
        UserEntity updatedValue = userRepo.save(userentity);
        BeanUtils.copyProperties(updatedValue, returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userentity = userRepo.findUserByUserId(userId);
        if (userentity == null) throw new UsernameNotFoundException(ErrorMessages.UNABLE_TO_FIND_USER.getErrorMessage());
        userRepo.delete(userentity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> userPage = userRepo.findAll(pageableRequest);
        List<UserEntity> users = userPage.getContent();

        for (UserEntity userentity : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userentity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userentity = userRepo.findUserByEmail(email);
        if (userentity == null) throw new UsernameNotFoundException(email);
        return new User(userentity.getEmail(), userentity.getEncryptedPassword(), new ArrayList<>());
    }
}
