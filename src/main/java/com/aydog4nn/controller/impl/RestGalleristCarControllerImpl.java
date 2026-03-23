package com.aydog4nn.controller.impl;

import com.aydog4nn.controller.IRestGalleristCarController;
import com.aydog4nn.controller.RestBaseController;
import com.aydog4nn.controller.RootEntity;
import com.aydog4nn.dto.DtoGalleristCar;
import com.aydog4nn.dto.DtoGalleristCarIU;
import com.aydog4nn.service.IGalleristCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }
}
