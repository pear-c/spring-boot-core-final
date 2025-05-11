package com.example.spring_boot_core_final;

import com.example.spring_boot_core_final.common.properties.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(FileProperties.class)
@SpringBootApplication
public class SpringBootCoreFinalApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCoreFinalApplication.class, args);
	}
}
