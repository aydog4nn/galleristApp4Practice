package com.aydog4nn.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CurrencyRatesResponse {


    private Integer totalCount;

    private List<CurrencyRatesItems> items;

    // {
    //    "totalCount": 1,
    //    "items": [
    //        {
    //            "Tarih": "23-03-2026",
    //            "TP_DK_USD_S_YTL": "44.21200000",
    //            "UNIXTIME": {
    //                "$numberLong": "1774213200"
    //            }
    //        }
    //    ]
    //}
}
