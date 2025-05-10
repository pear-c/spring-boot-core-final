package com.example.spring_boot_core_final.shell;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.account.service.AuthenticationService;
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
            return String.format("Account(id=%d, password=%s, name=%s)", id, password, account.getName());
        }
    }

    @ShellMethod
    public String logout() {
        if(authService.isLogin()) {
            authService.logout();
            return "good bye";
        }
        return "현재 로그인 상태가 아닙니다.";
    }

    @ShellMethod
    public String currentUser() {
        if(authService.isLogin()) {
            Account account = authService.getCurrentAccount();
            return String.format("Account(id=%d, password=%s, name=%s)", account.getId(), account.getPassword(), account.getName());
        }
        return "현재 로그인 상태가 아닙니다.";
    }

    @ShellMethod
    public String city() {
        List<String> cities = priceService.cities();
        StringBuilder sb = new StringBuilder("[");
        for(String city : cities) {
            sb.append(city).append(", ");
        }
        sb.replace(sb.length(), sb.length(), "]");

        return sb.toString();
    }

    @ShellMethod
    public String sector(String city) {
        return null;
    }

    @ShellMethod
    public String price(String city, String sector) {
        return null;
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return null;
    }

}
