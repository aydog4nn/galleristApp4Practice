package com.aydog4nn.service;

import com.aydog4nn.dto.DtoGalleristCar;
import com.aydog4nn.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

}
