package com.aydog4nn.controller;

import com.aydog4nn.dto.DtoGalleristCar;
import com.aydog4nn.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

}
