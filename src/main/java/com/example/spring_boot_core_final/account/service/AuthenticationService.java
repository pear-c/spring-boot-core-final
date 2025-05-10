package com.example.spring_boot_core_final.account.service;

import com.example.spring_boot_core_final.account.dto.Account;
import com.example.spring_boot_core_final.common.dataparser.DataParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Getter
public class AuthenticationService {
    private final DataParser dataParser;

    private Account currentAccount;

    public Account login(Long id, String password) {
        List<Account> accounts = dataParser.accounts();

        for(Account account : accounts) {
            long accountId = account.getId();
            String accountPassword = account.getPassword().trim();

            if(id == accountId && accountPassword.equals(password)) {
                this.currentAccount = account;
                return account;
            }
        }
        return null;
    }

    public void logout() {
        this.currentAccount = null;
    }

    public boolean isLogin() {
        return currentAccount != null;
    }
}

