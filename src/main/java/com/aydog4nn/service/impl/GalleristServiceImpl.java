package com.aydog4nn.service.impl;

import com.aydog4nn.dto.DtoAddress;
import com.aydog4nn.dto.DtoGallerist;
import com.aydog4nn.dto.DtoGalleristIU;
import com.aydog4nn.exception.BaseException;
import com.aydog4nn.exception.ErrorMessage;
import com.aydog4nn.exception.MessageType;
import com.aydog4nn.model.Address;
import com.aydog4nn.model.Gallerist;
import com.aydog4nn.repository.AddressRepository;
import com.aydog4nn.repository.GalleristRepository;
import com.aydog4nn.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;



    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU){

        Optional<Address> optionalAddress =  addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(dtoGalleristIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());


        BeanUtils.copyProperties(dtoGalleristIU,gallerist);
        gallerist.setAddress(optionalAddress.get());

        return gallerist;

    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));

        BeanUtils.copyProperties(savedGallerist,dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(),dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }
}
