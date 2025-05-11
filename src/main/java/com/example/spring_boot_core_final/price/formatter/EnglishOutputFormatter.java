package com.example.spring_boot_core_final.price.formatter;

import com.example.spring_boot_core_final.price.dto.Price;

public class EnglishOutputFormatter implements OutPutFormatter{

    @Override
    public String format(Price price, int usage) {
        String city = price.getCity().trim();
        String sector = price.getSector().trim();
        int unitPrice = price.getUnitPrice();
        int total = unitPrice * usage;

        return String.format("city: %s, sector: %s, unit price(won): %d, bill total(won): %d",
                city, sector, unitPrice, total);
    }
}
