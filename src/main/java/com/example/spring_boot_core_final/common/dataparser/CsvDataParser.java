package com.example.spring_boot_core_final.common.dataparser;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.common.properties.FileProperties;
import com.example.spring_boot_core_final.price.dto.Price;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@ConditionalOnProperty(name = "file.type", havingValue = "csv")
public class CsvDataParser implements DataParser{

    private final FileProperties fileProperties;
    private List<Price> prices;
    private List<Account> accounts;

    // 미리 파일 값 파싱 : 메서드 실행 시마다 파일 값 읽는 것 비효율
    @PostConstruct
    public void init() {
        try(InputStreamReader input = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(fileProperties.getPricePath()))) {
            prices = new CsvToBeanBuilder<Price>(input)
                    .withType(Price.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            // 모든 필드 trim 보장
            for (Price price : prices) {
                price.setCity(price.getCity().trim());
                price.setSector(price.getSector().trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(InputStreamReader input = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(fileProperties.getAccountPath()))) {
            accounts = new CsvToBeanBuilder<Account>(input)
                    .withType(Account.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            for(Account account : accounts) {
                account.setName(account.getName().trim());
                account.setPassword(account.getPassword().trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> cities() {
        List<String> cities = new ArrayList<>();
        for(Price price : prices) {
            String cityName = price.getCity();
            if(!cities.contains(cityName)) {
                cities.add(cityName);
            }
        }
        return cities;
    }
    @Override
    public List<String> sectors(String city) {
        List<String> sectors = new ArrayList<>();
        for(Price price : prices) {
            String sector = price.getSector();

            if(city.equals(price.getCity()) && !sectors.contains(sector)) {
                sectors.add(sector);
            }
        }
        return sectors;
    }
    @Override
    public Price price(String city, String sector) {
        for(Price price : prices) {
            String cityName = price.getCity();
            String sectorName = price.getSector();
            if(cityName.equals(city) && sectorName.equals(sector)) {
                return price;
            }
        }
        return null;
    }
    @Override
    public List<Account> accounts() {
        return accounts;
    }
}
