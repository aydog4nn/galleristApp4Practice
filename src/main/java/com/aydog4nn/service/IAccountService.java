package com.aydog4nn.service;

import com.aydog4nn.dto.DtoAccount;
import com.aydog4nn.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

}
