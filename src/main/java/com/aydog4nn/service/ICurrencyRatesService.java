package com.aydog4nn.service;

import com.aydog4nn.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {
    public CurrencyRatesResponse getCurrencyRates(String startDate,String endDate);
}
