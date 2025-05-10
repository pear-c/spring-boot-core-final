package com.example.spring_boot_core_final.common.dataparser;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.price.dto.Price;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
@ConditionalOnProperty(name = "parser", havingValue = "csv")
public class CsvDataParser implements DataParser{
    @Override
    public List<String> cities() {
        return null;
    }
    @Override
    public List<String> sectors(String city) {
        return null;
    }
    @Override
    public Price price(String city, String sector) {
        return null;
    }
    @Override
    public List<Account> accounts() {
        try(InputStreamReader input = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("account.csv"))) {
            return new CsvToBeanBuilder<Account>(input)
                    .withType(Account.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
