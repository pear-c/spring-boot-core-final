package com.example.spring_boot_core_final.common.dataparser;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.price.dto.Price;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "parser", havingValue = "csv")
public class CsvDataParser implements DataParser{
    @Override
    public List<String> cities() {
        try(InputStreamReader input = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("Tariff.csv"))) {

            List<Price> prices = new CsvToBeanBuilder<Price>(input)
                    .withType(Price.class)
                    .build()
                    .parse();

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
        try(InputStreamReader input = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("Tariff.csv"))) {

            List<Price> prices = new CsvToBeanBuilder<Price>(input)
                    .withType(Price.class)
                    .build()
                    .parse();

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
        try(InputStreamReader input = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("Tariff.csv"))) {

            List<Price> prices = new CsvToBeanBuilder<Price>(input)
                    .withType(Price.class)
                    .build()
                    .parse();

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
