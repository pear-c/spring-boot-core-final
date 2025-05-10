package com.example.spring_boot_core_final.price.service;

import com.example.spring_boot_core_final.price.dto.Price;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    public List<String> cities() {
        return null;
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
