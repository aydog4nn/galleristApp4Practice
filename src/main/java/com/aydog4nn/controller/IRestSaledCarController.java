package com.aydog4nn.controller;

import com.aydog4nn.dto.DtoSaledCar;
import com.aydog4nn.dto.DtoSaledCarIU;

public interface IRestSaledCarController {
    public RootEntity<DtoSaledCar>  buyCar(DtoSaledCarIU dtoSaledCarIU);

}
