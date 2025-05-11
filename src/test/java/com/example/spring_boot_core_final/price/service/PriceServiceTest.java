package com.example.spring_boot_core_final.price.service;

import com.example.spring_boot_core_final.common.dataparser.DataParser;
import com.example.spring_boot_core_final.price.dto.Price;
import com.example.spring_boot_core_final.price.formatter.OutPutFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private DataParser dataParser;
    @Mock
    private OutPutFormatter formatter;

    @InjectMocks
    private PriceService priceService;

    @Test
    void billTotal() {
        String city = "동두천시";
        String sector = "가정용";
        int usage = 10;

        Price mockPrice = new Price();
        mockPrice.setCity(city);
        mockPrice.setSector(sector);
        mockPrice.setUnitPrice(690);

        Mockito.when(dataParser.price(city, sector)).thenReturn(mockPrice);
        Mockito.when(formatter.format(mockPrice, usage)).thenReturn("Price(id=1, city=동두천, sector=가정용, uniPrice=690)");

        String result = priceService.billTotal(city, sector, usage);

        assertEquals("Price(id=1, city=동두천, sector=가정용, uniPrice=690)", result);
        Mockito.verify(dataParser).price(city, sector);
        Mockito.verify(formatter).format(mockPrice, usage);
    }
}