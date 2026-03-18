package com.aydog4nn.service.impl;

import com.aydog4nn.dto.AuthRequest;
import com.aydog4nn.dto.AuthResponse;
import com.aydog4nn.dto.DtoUser;
import com.aydog4nn.exception.BaseException;
import com.aydog4nn.exception.ErrorMessage;
import com.aydog4nn.exception.MessageType;
import com.aydog4nn.jwt.JWTService;
import com.aydog4nn.model.RefreshToken;
import com.aydog4nn.model.User;
import com.aydog4nn.repository.RefreshTokenRepository;
import com.aydog4nn.repository.UserRepository;
import com.aydog4nn.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JWTService jwtService;

    private User createUser(AuthRequest authRequest){
        User newUser = new User();
        newUser.setCreateTime(new Date());
        newUser.setUsername(authRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        return newUser;
    }

    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();
        User savedUser = userRepository.save(createUser(authRequest));

        BeanUtils.copyProperties(savedUser,dtoUser);
        return dtoUser;

    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());

            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optionalUser = userRepository.findByUsername(authRequest.getUsername());

            String accessToken =  jwtService.generateToken(optionalUser.get());
            RefreshToken savedRefreshToken =  refreshTokenRepository.save(createRefreshToken(optionalUser.get()));

            return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());

        }
        catch (Exception e) {
            throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.USERNAME_OR_PASSWORD_INVALID));
        }
    }
}
