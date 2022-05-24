package com.example.vacation_project.service.util;

import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.account.AccountRepository;
import com.example.vacation_project.exception.NotFoundException;
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
