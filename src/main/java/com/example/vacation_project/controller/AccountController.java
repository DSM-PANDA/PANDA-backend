package com.example.vacation_project.controller;

import com.example.vacation_project.dto.response.AccountNameResponse;
import com.example.vacation_project.dto.response.PostListResponse;
import com.example.vacation_project.dto.response.PostResponse;
import com.example.vacation_project.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account")
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/name")
    public AccountNameResponse name() {
        return accountService.getname();
    }

    @GetMapping("/{postId}")
    public PostResponse post(@PathVariable("postId") Long postId) {
        return accountService.getpost(postId);
    }

    @GetMapping("/post")
    public PostListResponse postlist(Pageable page) {
        return accountService.getpostList(page);
    }

}
