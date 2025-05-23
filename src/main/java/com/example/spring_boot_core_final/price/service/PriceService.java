package com.example.spring_boot_core_final.price.service;

import com.example.spring_boot_core_final.common.dataparser.DataParser;
import com.example.spring_boot_core_final.price.dto.Price;
import com.example.spring_boot_core_final.price.formatter.OutPutFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PriceService {
    private final DataParser dataParser;
    private final OutPutFormatter formatter;

    public List<String> cities() {
        return dataParser.cities();
    }
    public List<String> sectors(String city) {
        return dataParser.sectors(city);
    }
    public Price price(String city, String sector) {
        return dataParser.price(city, sector);
    }
    public String billTotal(String city, String sector, int usage) {
        Price price = dataParser.price(city, sector);
        return formatter.format(price, usage);
    }
}
