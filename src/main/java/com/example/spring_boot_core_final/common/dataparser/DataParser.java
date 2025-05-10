package com.example.spring_boot_core_final.common.dataparser;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.price.dto.Price;

import java.util.List;

public interface DataParser {
    List<String> cities();
    List<String> sectors(String city);
    Price price(String city, String sector);
    List<Account> accounts();
}
