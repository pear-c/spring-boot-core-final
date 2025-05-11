package com.example.spring_boot_core_final;

import com.example.spring_boot_core_final.price.formatter.EnglishOutputFormatter;
import com.example.spring_boot_core_final.price.formatter.OutPutFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "file.type=csv",
        "file.price-path=Tariff.csv",
        "file.account-path=account.csv"
})
@ActiveProfiles("eng")
public class EngProfileTest {

    @Autowired
    private OutPutFormatter outPutFormatter;

    @Test
    void beanTest() {
        assertInstanceOf(EnglishOutputFormatter.class, outPutFormatter);
    }


}
