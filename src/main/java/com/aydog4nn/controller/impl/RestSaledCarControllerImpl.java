package com.aydog4nn.controller.impl;

import com.aydog4nn.controller.IRestSaledCarController;
import com.aydog4nn.controller.RestBaseController;
import com.aydog4nn.controller.RootEntity;
import com.aydog4nn.dto.DtoSaledCar;
import com.aydog4nn.dto.DtoSaledCarIU;
import com.aydog4nn.service.ISaledCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/api/saled-car")
@RestController
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {

    @Autowired
    private ISaledCarService saledCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
        return ok(saledCarService.buyCar(dtoSaledCarIU));
    }
}
