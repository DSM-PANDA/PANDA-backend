package com.example.vacation_project.controller;

import com.example.vacation_project.dto.reqest.AccountReqest;
import com.example.vacation_project.dto.JwtToken;
import com.example.vacation_project.dto.reqest.LoginRequest;
import com.example.vacation_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken signup(@Valid @RequestBody AccountReqest reqest) {
        return authService.signup(reqest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken login(@Valid @RequestBody LoginRequest reqest) {
        return authService.login(reqest);
    }

    @PutMapping("/reissue")
    public JwtToken reissue(@Valid @RequestBody JwtToken jwtToken) {
        return authService.tokenRefresh(jwtToken);
    }

    @PatchMapping("/{accountId}")
    public void id(@PathVariable("accountId") String accountId) {
        authService.id(accountId);
    }

}
