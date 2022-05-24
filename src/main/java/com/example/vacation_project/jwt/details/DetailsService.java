package com.example.vacation_project.jwt.details;

import com.example.vacation_project.entity.account.AccountRepository;
import com.example.vacation_project.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public Details loadUserByUsername(String id) throws UsernameNotFoundException {

        return accountRepository.findByAccountId(id)
                .map(Details::new)
                .orElseThrow(NotFoundException::new);
    }

}
