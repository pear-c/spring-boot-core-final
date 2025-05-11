package com.example.spring_boot_core_final.common.config;

import com.example.spring_boot_core_final.price.formatter.EnglishOutputFormatter;
import com.example.spring_boot_core_final.price.formatter.KoreanOutputFormatter;
import com.example.spring_boot_core_final.price.formatter.OutPutFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterConfig {

    @Value("${formatter.language}")
    private String language;

    @Bean
    public OutPutFormatter outPutFormatter() {
        if("kor".equals(language)) {
            return new KoreanOutputFormatter();
        } else {
            return new EnglishOutputFormatter();
        }
    }
}
