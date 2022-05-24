package com.example.vacation_project.service;

import com.example.vacation_project.dto.reqest.AccountReqest;
import com.example.vacation_project.dto.JwtToken;
import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.account.AccountRepository;
import com.example.vacation_project.entity.refreshToken.RefreshToken;
import com.example.vacation_project.entity.refreshToken.RefreshTokenRepository;
import com.example.vacation_project.exception.ConflictException;
import com.example.vacation_project.exception.NotFoundException;
import com.example.vacation_project.exception.UnauthorizedException;
import com.example.vacation_project.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;


    // 로그인
    public JwtToken login (AccountReqest reqest) {

        Account account = accountRepository.findByAccountId(reqest.getAccountId())
                .orElseThrow(NotFoundException::new);

        if(!encoder.matches(reqest.getPwd(), account.getPwd())) {
            throw new NotFoundException();
        }

        // 토큰 발행
        return JwtToken.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(account.getAccountId()))
                .refreshToken(jwtTokenProvider.getRefreshToken(account.getAccountId()))
                .build();

    }

    // 회원가입
    public JwtToken signup (AccountReqest reqest) {

        // 회원정보 저장
        String accountId = accountRepository.save(
                Account.builder()
                        .name(reqest.getName())
                        .accountId(reqest.getAccountId())
                        .pwd(encoder.encode(reqest.getPwd()))
                        .build()
        ).getAccountId();

        // 토큰 발행
        return JwtToken.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(accountId))
                .refreshToken(jwtTokenProvider.getRefreshToken(accountId))
                .build();

    }

    // 토큰 재발급
    @Transactional
    public JwtToken tokenRefresh(JwtToken jwtToken) {

        Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findById(authentication.getName())
                .orElseThrow(NotFoundException::new);

        if (!refreshToken.getRefreshToken().equals(jwtToken.getRefreshToken())) {
            throw new UnauthorizedException();
        }

        JwtToken token = JwtToken.builder()
                .refreshToken(refreshToken.getRefreshToken())
                .accessToken(jwtTokenProvider.generateAccessToken(authentication.getName()))
                .build();

        RefreshToken newRefreshToken = refreshToken.update(token.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return token;

    }

    // 아이디 중복 확인하기
    public void id(String accountId) {

        if(accountRepository.existsByAccountId(accountId)) {
            throw new ConflictException();
        }

    }


}
