package com.example.autowired.test.autow22.service;

import com.example.autowired.test.autow22.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAddressesByUSer(String id);
}
