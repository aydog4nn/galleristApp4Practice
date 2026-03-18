package com.aydog4nn.service;

import com.aydog4nn.dto.AuthRequest;
import com.aydog4nn.dto.AuthResponse;
import com.aydog4nn.dto.DtoUser;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest authRequest);

    public AuthResponse authenticate(AuthRequest authRequest);

}
