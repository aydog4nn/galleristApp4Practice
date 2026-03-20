package com.aydog4nn.service.impl;

import com.aydog4nn.dto.DtoAccount;
import com.aydog4nn.dto.DtoAccountIU;
import com.aydog4nn.model.Account;
import com.aydog4nn.repository.AccountRepository;
import com.aydog4nn.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {


    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU){
        Account account = new Account();
        account.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAccountIU,account);
        return account;
    }


    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        DtoAccount dtoAccount = new DtoAccount();
        Account savedAccount =  accountRepository.save(createAccount(dtoAccountIU));

        BeanUtils.copyProperties(savedAccount,dtoAccount);
        return dtoAccount;
    }
}
