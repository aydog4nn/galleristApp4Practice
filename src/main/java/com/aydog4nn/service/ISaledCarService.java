package com.aydog4nn.service;

import com.aydog4nn.dto.DtoSaledCar;
import com.aydog4nn.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
