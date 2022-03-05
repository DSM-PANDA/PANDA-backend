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

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken signup(@Valid @RequestBody AccountReqest reqest) {
        return authService.signup(reqest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken login(@Valid @RequestBody AccountReqest reqest) {
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
