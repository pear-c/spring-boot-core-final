package com.example.spring_boot_core_final.shell;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.account.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class MyCommands {

    private final AuthenticationService authService;

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
        return null;
    }

    @ShellMethod
    public String city() {
        return null;
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
