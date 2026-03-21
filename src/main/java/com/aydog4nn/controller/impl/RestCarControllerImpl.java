package com.aydog4nn.controller.impl;

import com.aydog4nn.controller.IRestCarController;
import com.aydog4nn.controller.RestBaseController;
import com.aydog4nn.controller.RootEntity;
import com.aydog4nn.dto.DtoCar;
import com.aydog4nn.dto.DtoCarIU;
import com.aydog4nn.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/api/car")
@RestController
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }
}
