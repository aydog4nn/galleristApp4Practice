package com.aydog4nn.model;


import com.aydog4nn.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account extends BaseEntity{

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "iban")
    private String iban;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

}
