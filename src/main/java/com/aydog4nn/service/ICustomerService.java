package com.aydog4nn.service;

import com.aydog4nn.dto.DtoCustomer;
import com.aydog4nn.dto.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

}
