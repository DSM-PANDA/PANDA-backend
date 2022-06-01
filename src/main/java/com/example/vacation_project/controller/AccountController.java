package com.example.vacation_project.controller;

import com.example.vacation_project.dto.response.AccountIdResponse;
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

    @GetMapping
    public AccountIdResponse getAccountId() {
        return accountService.getAccountId();
    }

    @GetMapping("/{postId}")
    public PostResponse getpost(@PathVariable("postId") Long postId) {
        return accountService.getpost(postId);
    }

    @GetMapping("/post")
    public PostListResponse getpostlist(Pageable page) {
        return accountService.getpostList(page);
    }

}
