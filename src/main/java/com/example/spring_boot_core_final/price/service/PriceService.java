package com.example.spring_boot_core_final.price.service;

import com.example.spring_boot_core_final.common.dataparser.DataParser;
import com.example.spring_boot_core_final.price.dto.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PriceService {
    private final DataParser dataParser;

    public List<String> cities() {
        return dataParser.cities();
    }
    public List<String> sectors(String city) {
        return null;
    }
    public Price price(String city, String sector) {
        return null;
    }
    public String billTotal(String city, String sector, int usage) {
        return null;
    }
}
