package com.aydog4nn.service.impl;

import com.aydog4nn.dto.*;
import com.aydog4nn.exception.BaseException;
import com.aydog4nn.exception.ErrorMessage;
import com.aydog4nn.exception.MessageType;
import com.aydog4nn.model.Car;
import com.aydog4nn.model.Gallerist;
import com.aydog4nn.model.GalleristCar;
import com.aydog4nn.repository.CarRepository;
import com.aydog4nn.repository.GalleristCarRepository;
import com.aydog4nn.repository.GalleristRepository;
import com.aydog4nn.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU){

        Optional<Gallerist> optionalGallerist =  galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if (optionalGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(dtoGalleristCarIU.getGalleristId().toString(),MessageType.NO_RECORD_EXIST));
        }

        Optional<Car> optionalCar =  carRepository.findById(dtoGalleristCarIU.getCarId());
        if (optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(dtoGalleristCarIU.getCarId().toString(),MessageType.NO_RECORD_EXIST));
        }



        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optionalGallerist.get());
        galleristCar.setCar(optionalCar.get());

        return galleristCar;

    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();

        GalleristCar savedGalleristCar =  galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));


        BeanUtils.copyProperties(savedGalleristCar,dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(),dtoAddress);
        BeanUtils.copyProperties(savedGalleristCar.getCar(),dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }
}
