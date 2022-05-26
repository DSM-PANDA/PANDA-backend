package com.example.vacation_project.facade;

import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.account.AccountRepository;
import com.example.vacation_project.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountFacade {

    private final AccountRepository accountRepository;

    public Account findByAccountId(String accountId) {
        return accountRepository.findByAccountId(accountId)
                .orElseThrow(NotFoundException::new);
    }

}
