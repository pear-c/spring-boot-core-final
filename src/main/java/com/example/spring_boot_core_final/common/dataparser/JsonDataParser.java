package com.example.spring_boot_core_final.common.dataparser;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.common.properties.FileProperties;
import com.example.spring_boot_core_final.price.dto.Price;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
@ConditionalOnProperty(name = "file.type", havingValue = "json")
public class JsonDataParser implements DataParser{

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FileProperties fileProperties;

    private List<Price> prices;
    private List<Account> accounts;

    // 미리 파일 값 파싱 : 메서드 실행 시마다 파일 값 읽는 것 비효율
    @PostConstruct
    public void init() {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream(fileProperties.getPricePath())) {
            prices = objectMapper.readValue(input, new TypeReference<>() {});

            // 모든 필드 trim 보장
            for (Price price : prices) {
                price.setCity(price.getCity().trim());
                price.setSector(price.getSector().trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(InputStream input = getClass().getClassLoader().getResourceAsStream(fileProperties.getAccountPath())) {
            accounts = objectMapper.readValue(input, new TypeReference<>() {});

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
