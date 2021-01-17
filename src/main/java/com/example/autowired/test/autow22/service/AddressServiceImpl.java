package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.dto.AddressDTO;
import com.example.autowired.test.autow22.entity.AddressEntity;
import com.example.autowired.test.autow22.entity.UserEntity;
import com.example.autowired.test.autow22.repo.AddressRepo;
import com.example.autowired.test.autow22.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AddressRepo addressRepo;

    @Override
    public List<AddressDTO> getAddressesByUSer(String id) {
        ModelMapper modelMapper = new ModelMapper();
        List<AddressDTO> returnValue = new ArrayList<>();
        UserEntity userEntity = userRepo.findUserByUserId(id);
        if(userEntity ==null)return returnValue;
        Iterable<AddressEntity> addresses = addressRepo.findAllByUserEntity(userEntity);
        for(AddressEntity entity:addresses){
            returnValue.add(modelMapper.map(entity, AddressDTO.class));
        }
        return returnValue;
    }
}
