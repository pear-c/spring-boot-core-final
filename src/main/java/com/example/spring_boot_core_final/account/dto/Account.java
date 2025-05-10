package com.example.spring_boot_core_final.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Account {
    @CsvBindByName(column = "아이디")
    @JsonProperty("아이디")
    long id;
    @CsvBindByName(column = "비밀번호")
    @JsonProperty("비밀번호")
    String password;
    @CsvBindByName(column = "이름")
    @JsonProperty("이름")
    String name;
}
