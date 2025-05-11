package com.example.spring_boot_core_final.common.dataparser;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.price.dto.Price;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Component
@ConditionalOnProperty(name = "parser", havingValue = "json")
public class JsonDataParser implements DataParser{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<String> cities() {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("Tariff.json")) {
            List<Price> prices = objectMapper.readValue(input, new TypeReference<>() {});

            List<String> cities = new ArrayList<>();
            for(Price price : prices) {
                String cityName = price.getCity().trim();
                if(!cities.contains(cityName)) {
                    cities.add(cityName);
                }
            }
            return cities;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<String> sectors(String city) {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("Tariff.json")) {
            List<Price> prices = objectMapper.readValue(input, new TypeReference<>() {});

            List<String> sectors = new ArrayList<>();
            for(Price price : prices) {
                String sector = price.getSector().trim();
                if(city.equals(price.getCity().trim()) && !sectors.contains(sector)) {
                    sectors.add(sector);
                }
            }
            return sectors;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Price price(String city, String sector) {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("Tariff.json")) {
            List<Price> prices = objectMapper.readValue(input, new TypeReference<>() {});

            for(Price price : prices) {
                String cityName = price.getCity().trim();
                String sectorName = price.getSector().trim();
                if(cityName.equals(city) && sectorName.equals(sector)) {
                    return price;
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Account> accounts() {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("account.json")) {
            return objectMapper.readValue(input, new TypeReference<>() {});

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
