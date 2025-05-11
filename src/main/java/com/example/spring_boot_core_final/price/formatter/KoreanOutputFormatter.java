package com.example.spring_boot_core_final.price.formatter;

import com.example.spring_boot_core_final.price.dto.Price;

public class KoreanOutputFormatter implements OutPutFormatter{

    @Override
    public String format(Price price, int usage) {
        String city = price.getCity().trim();
        String sector = price.getSector().trim();
        int unitPrice = price.getUnitPrice();
        int total = unitPrice * usage;

        return String.format("지자체명: %s, 업종: %s, 구간금액(원): %d, 총금액(원): %d",
                city, sector, unitPrice, total);
    }
}
