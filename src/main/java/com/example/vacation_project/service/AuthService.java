package com.example.vacation_project.service;

import com.example.vacation_project.dto.reqest.AccountReqest;
import com.example.vacation_project.dto.JwtToken;
import com.example.vacation_project.dto.reqest.LoginRequest;
import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.account.AccountRepository;
import com.example.vacation_project.entity.refreshToken.RefreshToken;
import com.example.vacation_project.entity.refreshToken.RefreshTokenRepository;
import com.example.vacation_project.exception.ConflictException;
import com.example.vacation_project.exception.NotFoundException;
import com.example.vacation_project.exception.UnauthorizedException;
import com.example.vacation_project.facade.AccountFacade;
import com.example.vacation_project.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountFacade accountFacade;

    public JwtToken login (LoginRequest reqest) {

        Account account = accountFacade.findByAccountId(reqest.getAccountId());

        if(!encoder.matches(reqest.getPassword(), account.getPassword())) {
            throw new ConflictException("비밀번호가 일치하지 않습니다");
        }

        return JwtToken.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(account.getAccountId()))
                .refreshToken(jwtTokenProvider.getRefreshToken(account.getAccountId()))
                .build();

    }

    public JwtToken signup (AccountReqest reqest) {

        String accountId = accountRepository.save(
                Account.builder()
                        .name(reqest.getName())
                        .accountId(reqest.getAccountId())
                        .password(encoder.encode(reqest.getPassword()))
                        .build()
        ).getAccountId();

        return JwtToken.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(accountId))
                .refreshToken(jwtTokenProvider.getRefreshToken(accountId))
                .build();

    }

    public JwtToken refreshToken(JwtToken jwtToken) {

        if(jwtTokenProvider.validateToken(jwtToken.getAccessToken())) {
            throw new UnauthorizedException("access 토큰이 유효합니다");
        }

        if (!jwtTokenProvider.isRefreshToken(jwtToken.getRefreshToken())) {
            throw new UnauthorizedException("refres 토큰을 확인해 주세요");
        }

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(jwtToken.getRefreshToken())
                .orElseThrow(() -> new NotFoundException("일치하는 토큰을 찾을 수 없습니다."));

        JwtToken token = JwtToken.builder()
                .refreshToken(refreshToken.getRefreshToken())
                .accessToken(jwtTokenProvider.generateAccessToken(refreshToken.getId()))
                .build();

        RefreshToken newRefreshToken = refreshToken.update(token.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return token;

    }

    public void existsByAccountId(String accountId) {
        if(accountRepository.existsByAccountId(accountId)) {
            throw new ConflictException("중복된 아이디입니다.");
        }
    }

}
