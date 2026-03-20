package com.aydog4nn.service;

import com.aydog4nn.dto.DtoAddress;
import com.aydog4nn.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

}
