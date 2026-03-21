package com.aydog4nn.service;

import com.aydog4nn.dto.DtoCar;
import com.aydog4nn.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);

}
