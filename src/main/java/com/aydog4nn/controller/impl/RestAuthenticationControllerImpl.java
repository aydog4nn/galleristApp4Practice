package com.aydog4nn.controller.impl;

import com.aydog4nn.controller.IRestAuthenticationController;
import com.aydog4nn.controller.RestBaseController;
import com.aydog4nn.controller.RootEntity;
import com.aydog4nn.dto.AuthRequest;
import com.aydog4nn.dto.AuthResponse;
import com.aydog4nn.dto.DtoUser;
import com.aydog4nn.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

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
}
