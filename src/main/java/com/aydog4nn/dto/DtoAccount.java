package com.aydog4nn.dto;

import com.aydog4nn.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DtoAccount {

    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;


}
