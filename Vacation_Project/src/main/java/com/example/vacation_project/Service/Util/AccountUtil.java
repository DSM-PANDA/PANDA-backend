package com.example.vacation_project.Service.Util;

import com.example.vacation_project.Entity.Account.Account;
import com.example.vacation_project.Entity.Account.AccountRepository;
import com.example.vacation_project.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountUtil {

    private final AccountRepository accountRepository;
    private final AuthenticationUtil authenticationUtil;

    public Account getAccount() {
        return accountRepository.findByAccountId(authenticationUtil.getAccountId())
                .orElseThrow(NotFoundException::new);
    }

}
