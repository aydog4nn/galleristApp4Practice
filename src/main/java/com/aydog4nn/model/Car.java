package com.aydog4nn.model;


import com.aydog4nn.enums.CarStatusType;
import com.aydog4nn.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car extends BaseEntity{

    @Column(name = "plaka")
    private String plaka;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "production_year")
    private Integer productionYear;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "damage_price")
    private BigDecimal damagePrice;

    @Column(name = "car_status_type")
    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;

}


