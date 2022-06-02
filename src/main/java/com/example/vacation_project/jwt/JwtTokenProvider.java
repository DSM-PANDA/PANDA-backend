package com.example.vacation_project.jwt;

import com.example.vacation_project.jwt.details.Details;
import com.example.vacation_project.jwt.details.DetailsService;
import com.example.vacation_project.entity.refreshToken.RefreshToken;
import com.example.vacation_project.entity.refreshToken.RefreshTokenRepository;
import com.example.vacation_project.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private Long accessTokenTime;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshTokenTime;

    private final DetailsService detailsService;

    private final RefreshTokenRepository refreshTokenRepository;

    // RefreshToken 저장
    public String getRefreshToken(String accountId) {
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(accountId)
                        .refreshToken(generateRefreshToken(accountId))
                        .build()
        ).getRefreshToken();
    }

    // AccessToken 생성
    public String generateAccessToken(String accountId) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenTime * 1000))
                .setIssuedAt(new Date())
                .setHeaderParam("typ", "access")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(accountId)
                .compact();
    }

    // RefreshToken 생성
    public String generateRefreshToken(String accountId) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTime * 1000))
                .setIssuedAt(new Date())
                .setHeaderParam("typ", "refresh")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(accountId)
                .compact();
    }


    // 권한 가져오기
    public Authentication getAuthentication(String token) {

        Details details = detailsService.loadUserByUsername(subject(token));
        return new UsernamePasswordAuthenticationToken(details,"",details.getAuthorities());

    }

    // 토큰의 유효성 검증
    public boolean validateToken(String token) {

        try {
            return getBody(token).getExpiration().after(new Date());
        } catch (Exception e) {
            throw new UnauthorizedException("토큰을 확인해 주세요");
        }

    }

    public boolean isRefreshToken(String token) {
        try {
            return getHeader(token).get("typ").equals("refresh_token");
        } catch (Exception e) {
            throw new UnauthorizedException("토큰을 확인해 주세요");
        }
    }


    // 토큰의 Body를 불러옴
    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // 토큰의 제목 id를 불러옴
    private String subject(String token) {
        try {
            return getBody(token).getSubject();
        } catch (Exception e) {
            throw new UnauthorizedException("토큰을 확인해 주세요");
        }
    }

    private JwsHeader getHeader(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getHeader();
    }

}
