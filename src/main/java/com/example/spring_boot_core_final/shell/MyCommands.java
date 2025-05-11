package com.example.spring_boot_core_final.shell;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.account.service.AuthenticationService;
import com.example.spring_boot_core_final.price.dto.Price;
import com.example.spring_boot_core_final.price.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class MyCommands {

    private final AuthenticationService authService;
    private final PriceService priceService;

    @ShellMethod
    public String login(long id, String password) {
        Account account = authService.login(id, password);
        if(account == null) {
            return "로그인 실패 : 아이디 or 비밀번호가 일치하지 않습니다.";
        } else {
            return account.toString();
        }
    }

    @ShellMethod
    public String logout() {
        if(!authService.isLogin()) {
            return "현재 로그인 상태가 아닙니다.";
        }
        authService.logout();
        return "good bye";
    }

    @ShellMethod
    public String currentUser() {
        if(!authService.isLogin()) {
            return "현재 로그인 상태가 아닙니다.";
        }
        Account account = authService.getCurrentAccount();
        return account.toString();
    }

    @ShellMethod
    public String city() {
        if(!authService.isLogin()) {
            return "로그인 먼저 해주세요.";
        }

        List<String> cities = priceService.cities();
        StringBuilder sb = new StringBuilder();
        for(String city : cities) {
            sb.append(city).append(", ");
        }
        sb.delete(sb.length()-2, sb.length());

        return "[" + sb + "]";
    }

    @ShellMethod
    public String sector(String city) {
        if(!authService.isLogin()) {
            return "로그인 먼저 해주세요.";
        }

        List<String> sectors = priceService.sectors(city);
        if(sectors.isEmpty()) {
            return "잘못된 입력입니다.";
        }

        StringBuilder sb = new StringBuilder();
        for(String sector : sectors) {
            sb.append(sector).append(", ");
        }
        sb.delete(sb.length()-2, sb.length());

        return "[" + sb + "]";
    }

    @ShellMethod
    public String price(String city, String sector) {
        if(!authService.isLogin()) {
            return "로그인 먼저 해주세요.";
        }

        Price price = priceService.price(city, sector);
        if(price == null) {
            return "잘못된 입력입니다.";
        } else {
            return price.toString();
        }
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return null;
    }

}
