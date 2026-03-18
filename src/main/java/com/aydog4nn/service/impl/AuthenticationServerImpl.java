package com.aydog4nn.service.impl;

import com.aydog4nn.dto.AuthRequest;
import com.aydog4nn.dto.DtoUser;
import com.aydog4nn.model.User;
import com.aydog4nn.repository.UserRepository;
import com.aydog4nn.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServerImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User createUser(AuthRequest authRequest){
        User newUser = new User();
        newUser.setCreateTime(new Date());
        newUser.setUsername(authRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        return newUser;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();
        User savedUser = userRepository.save(createUser(authRequest));

        BeanUtils.copyProperties(savedUser,dtoUser);
        return dtoUser;

    }
}
