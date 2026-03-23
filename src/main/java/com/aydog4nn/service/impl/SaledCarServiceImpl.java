package com.aydog4nn.service.impl;

import com.aydog4nn.dto.*;
import com.aydog4nn.enums.CarStatusType;
import com.aydog4nn.exception.BaseException;
import com.aydog4nn.exception.ErrorMessage;
import com.aydog4nn.exception.MessageType;
import com.aydog4nn.model.Car;
import com.aydog4nn.model.Customer;
import com.aydog4nn.model.SaledCar;
import com.aydog4nn.repository.CarRepository;
import com.aydog4nn.repository.CustomerRepository;
import com.aydog4nn.repository.GalleristRepository;
import com.aydog4nn.repository.SaledCarRepository;
import com.aydog4nn.service.ICurrencyRatesService;
import com.aydog4nn.service.ISaledCarService;
import com.aydog4nn.utils.DateUtils;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @Autowired
    private SaledCarRepository saledCarRepository;


    public BigDecimal convertCustomerAmountUSD(Customer customer){

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
        BigDecimal bigDecimalUSD =  new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal customerUSDAmount =  customer.getAccount().getAmount().divide(bigDecimalUSD,3, RoundingMode.HALF_UP);

        return customerUSDAmount;
    }

    public boolean checkCarStatus(Long carId){
        Optional<Car> optionalCar =  carRepository.findById(carId);
        if (optionalCar.isPresent() && optionalCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())){
            return false;
        }
        return true;
    }

    public BigDecimal remaingCustomerAccount(Customer customer,Car car){
        BigDecimal customerUSDAmount =  convertCustomerAmountUSD(customer);
        BigDecimal remainingCustomerUSDAmount =  customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse =
                currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));

        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        return remainingCustomerUSDAmount.multiply(usd);
    }



    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU){

        Optional<Customer> optionalCustomer=  customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCustomerId().toString(),MessageType.NO_RECORD_EXIST));
        }

        Optional<Car> optionalCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCarId().toString(),MessageType.NO_RECORD_EXIST));
        }

        BigDecimal customerUSDAmount =  convertCustomerAmountUSD(optionalCustomer.get());
        if (customerUSDAmount.compareTo(optionalCar.get().getPrice()) == 0 || customerUSDAmount.compareTo(optionalCar.get().getPrice()) >0){
                return true;
        }
        return false;
    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU){
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {


        if (!checkAmount(dtoSaledCarIU)){
            throw new BaseException(new ErrorMessage("",MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH));
        }

        if (!checkCarStatus(dtoSaledCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCarId().toString(),MessageType.CAR_STATUS_IS_ALREADY_SALED));
        }
        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);

        carRepository.save(car);


        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remaingCustomerAccount(customer,car));
        customerRepository.save(customer);

        return toDTO(savedSaledCar);
    }

    public DtoSaledCar toDTO(SaledCar saledCar){
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();


        BeanUtils.copyProperties(saledCar,dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(),dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(),dtoCar);

        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setCar(dtoCar);

        return dtoSaledCar;
    }
}
