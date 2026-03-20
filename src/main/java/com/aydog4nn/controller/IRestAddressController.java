package com.aydog4nn.controller;

import com.aydog4nn.dto.DtoAddress;
import com.aydog4nn.dto.DtoAddressIU;

public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);

}
