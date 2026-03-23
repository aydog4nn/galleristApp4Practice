package com.aydog4nn.controller.impl;

import com.aydog4nn.controller.IRestAuthenticationController;
import com.aydog4nn.controller.RestBaseController;
import com.aydog4nn.controller.RootEntity;
import com.aydog4nn.dto.AuthRequest;
import com.aydog4nn.dto.AuthResponse;
import com.aydog4nn.dto.DtoUser;
import com.aydog4nn.dto.RefreshTokenRequest;
import com.aydog4nn.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.register(authRequest));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.authenticate(authRequest));
    }

    @PostMapping("/refresh-token")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
