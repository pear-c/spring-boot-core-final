package com.example.spring_boot_core_final.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Price {
    @CsvBindByName(column = "순번")
    @JsonProperty("순번")
    long id;
    @CsvBindByName(column = "지자체명")
    @JsonProperty("지자체명")
    String city;
    @CsvBindByName(column = "업종")
    @JsonProperty("업종")
    String sector;
    @CsvBindByName(column = "구간금액(원)")
    @JsonProperty("구간금액(원)")
    int unitPrice;
}
