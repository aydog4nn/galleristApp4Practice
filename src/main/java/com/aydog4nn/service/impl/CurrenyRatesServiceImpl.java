package com.aydog4nn.service.impl;

import com.aydog4nn.dto.CurrencyRatesResponse;
import com.aydog4nn.exception.BaseException;
import com.aydog4nn.exception.ErrorMessage;
import com.aydog4nn.exception.MessageType;
import com.aydog4nn.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrenyRatesServiceImpl implements ICurrencyRatesService {

    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {

        String rootURL = "https://evds3.tcmb.gov.tr/igmevdsms-dis/";
        String series = "TP.DK.USD.S.YTL";
        String type = "json";

        String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "1Vg48AwU8i");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<CurrencyRatesResponse> responseEntity =
                    restTemplate.exchange(
                            endpoint,
                            HttpMethod.GET,
                            httpEntity,
                            new ParameterizedTypeReference<CurrencyRatesResponse>() {}
                    );

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }

            throw new BaseException(
                    new ErrorMessage("Başarısız response", MessageType.CURRENCY_RATE_IS_OCCURED)
            );

        } catch (Exception e) {
            throw new BaseException(
                    new ErrorMessage(e.getMessage(), MessageType.CURRENCY_RATE_IS_OCCURED)
            );
        }
    }
}
