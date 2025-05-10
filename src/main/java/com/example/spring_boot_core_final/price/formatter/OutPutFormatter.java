package com.example.spring_boot_core_final.price.formatter;

import com.example.spring_boot_core_final.price.dto.Price;

public interface OutPutFormatter {
    String format(Price price, int usage);
}
