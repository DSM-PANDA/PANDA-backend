package com.example.vacation_project.Controller;

import com.example.vacation_project.Dto.Reqest.AccountReqest;
import com.example.vacation_project.Dto.JwtToken;
import com.example.vacation_project.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken signup(@Valid @RequestBody AccountReqest reqest) {
        return authService.signup(reqest);
    }

    // 로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken login(@Valid @RequestBody AccountReqest reqest) {
        return authService.login(reqest);
    }

    // 토큰 재발급
    @PutMapping("/reissue")
    public JwtToken reissue(@Valid @RequestBody JwtToken jwtToken) {
        return authService.tokenRefresh(jwtToken);
    }

    // 아이디 중복 확인
    @GetMapping("/{accountId}")
    public void id(@PathVariable("accountId") String accountId) {
        authService.id(accountId);
    }

}
