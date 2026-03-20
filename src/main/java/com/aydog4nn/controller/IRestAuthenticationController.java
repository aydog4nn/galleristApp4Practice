package com.aydog4nn.controller;

import com.aydog4nn.dto.AuthRequest;
import com.aydog4nn.dto.AuthResponse;
import com.aydog4nn.dto.DtoUser;
import com.aydog4nn.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest authRequest);

    public RootEntity<AuthResponse> authenticate(AuthRequest authRequest);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);

}
