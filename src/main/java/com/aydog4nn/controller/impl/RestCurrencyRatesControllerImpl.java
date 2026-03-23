package com.aydog4nn.controller.impl;

import com.aydog4nn.controller.IRestCurrencyRatesController;
import com.aydog4nn.controller.RestBaseController;
import com.aydog4nn.controller.RootEntity;
import com.aydog4nn.dto.CurrencyRatesResponse;
import com.aydog4nn.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/currency-rates")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {


    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @GetMapping("/")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrencyRates(startDate,endDate));
    }
}
