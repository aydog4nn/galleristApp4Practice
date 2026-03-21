package com.aydog4nn.controller;

import com.aydog4nn.dto.DtoCustomer;
import com.aydog4nn.dto.DtoCustomerIU;
import com.aydog4nn.model.Customer;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

}
