package com.aydog4nn.controller;

import com.aydog4nn.dto.AuthRequest;
import com.aydog4nn.dto.AuthResponse;
import com.aydog4nn.dto.DtoUser;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest authRequest);

    public RootEntity<AuthResponse> authenticate(AuthRequest authRequest);

}
