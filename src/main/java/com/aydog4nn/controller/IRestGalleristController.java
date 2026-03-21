package com.aydog4nn.controller;

import com.aydog4nn.dto.DtoGallerist;
import com.aydog4nn.dto.DtoGalleristIU;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

}
