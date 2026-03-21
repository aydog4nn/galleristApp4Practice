package com.aydog4nn.service.impl;

import com.aydog4nn.dto.DtoAccount;
import com.aydog4nn.dto.DtoAddress;
import com.aydog4nn.dto.DtoCustomer;
import com.aydog4nn.dto.DtoCustomerIU;
import com.aydog4nn.exception.BaseException;
import com.aydog4nn.exception.ErrorMessage;
import com.aydog4nn.exception.MessageType;
import com.aydog4nn.model.Account;
import com.aydog4nn.model.Address;
import com.aydog4nn.model.Customer;
import com.aydog4nn.repository.AccountRepository;
import com.aydog4nn.repository.AddressRepository;
import com.aydog4nn.repository.CustomerRepository;
import com.aydog4nn.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;


    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Optional<Address> optionalAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(dtoCustomerIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Account> optionalAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(dtoCustomerIU.getAccountId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU,customer);
        customer.setAddress(optionalAddress.get());
        customer.setAccount(optionalAccount.get());

        return null;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();


        Customer savedCustomer =  customerRepository.save(Objects.requireNonNull(createCustomer(dtoCustomerIU)));
        BeanUtils.copyProperties(savedCustomer,dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(),dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(),dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;
    }
}
