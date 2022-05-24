package com.example.vacation_project.controller;

import com.example.vacation_project.dto.reqest.PostRequst;
import com.example.vacation_project.dto.response.PostIdResponse;
import com.example.vacation_project.dto.response.PostListResponse;
import com.example.vacation_project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/{accountId}")
@RequiredArgsConstructor
@RestController

public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public PostIdResponse savePost(@RequestBody @Valid PostRequst requst,
                                   @PathVariable("accountId") String accountId) {
        return postService.savePost(requst, accountId);
    }

    @GetMapping
    public PostListResponse postList(@PathVariable("accountId") String accountId, Pageable page) {
        return postService.getPostList(accountId, page);
    }

}
