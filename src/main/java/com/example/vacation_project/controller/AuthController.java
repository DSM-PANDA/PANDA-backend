package com.example.vacation_project.controller;

import com.example.vacation_project.dto.reqest.AccountReqest;
import com.example.vacation_project.dto.JwtToken;
import com.example.vacation_project.dto.reqest.LoginRequest;
import com.example.vacation_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{accountId}")
    public void existsByAccountId(@PathVariable("accountId") String accountId) {
        authService.existsByAccountId(accountId);
    }

}
