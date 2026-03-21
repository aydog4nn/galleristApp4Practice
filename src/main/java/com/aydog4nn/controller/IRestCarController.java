package com.aydog4nn.controller;

import com.aydog4nn.dto.DtoCar;
import com.aydog4nn.dto.DtoCarIU;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
