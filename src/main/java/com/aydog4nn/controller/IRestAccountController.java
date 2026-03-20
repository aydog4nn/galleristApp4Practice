package com.aydog4nn.controller;

import com.aydog4nn.dto.DtoAccount;
import com.aydog4nn.dto.DtoAccountIU;

public interface IRestAccountController {
    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
