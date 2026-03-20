package com.aydog4nn.service.impl;

import com.aydog4nn.dto.DtoAddress;
import com.aydog4nn.dto.DtoAddressIU;
import com.aydog4nn.exception.ErrorMessage;
import com.aydog4nn.model.Address;
import com.aydog4nn.repository.AddressRepository;
import com.aydog4nn.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU){
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU,address);
        return address;
    }


    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();
        Address address = addressRepository.save(createAddress(dtoAddressIU));

        BeanUtils.copyProperties(address,dtoAddress);
        return dtoAddress;
    }
}
