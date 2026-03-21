package com.aydog4nn.controller.impl;

import com.aydog4nn.controller.IRestCustomerController;
import com.aydog4nn.controller.RootEntity;
import com.aydog4nn.dto.DtoCustomer;
import com.aydog4nn.dto.DtoCustomerIU;
import com.aydog4nn.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aydog4nn.controller.RootEntity.ok;

@RequestMapping("/rest/api/customer")
@RestController
public class RestCustomerControllerImpl implements IRestCustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.saveCustomer(dtoCustomerIU));
    }
}
