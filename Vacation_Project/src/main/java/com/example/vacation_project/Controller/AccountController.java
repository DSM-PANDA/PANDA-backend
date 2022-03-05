package com.example.vacation_project.Controller;

import com.example.vacation_project.Dto.Response.AccountNameResponse;
import com.example.vacation_project.Dto.Response.PostListResponse;
import com.example.vacation_project.Dto.Response.PostResponse;
import com.example.vacation_project.Service.AccountService;
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
