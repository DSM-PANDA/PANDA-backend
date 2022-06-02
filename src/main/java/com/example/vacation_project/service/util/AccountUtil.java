package com.example.vacation_project.service.util;

import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.account.AccountRepository;
import com.example.vacation_project.exception.NotFoundException;
import com.example.vacation_project.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountUtil {

    private final AccountRepository accountRepository;

    public String getAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UnauthorizedException("권한이 존재하지 않습니다");
        }
        return authentication.getName();
    }

    public Account getAccount() {
        return accountRepository.findByAccountId(getAccountId())
                .orElseThrow(() -> new NotFoundException("일치하는 user 아이디를 찾을 수 없습니다"));
    }

}
