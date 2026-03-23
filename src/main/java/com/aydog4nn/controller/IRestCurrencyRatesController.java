package com.aydog4nn.controller;

import com.aydog4nn.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate,String endDate);

}
