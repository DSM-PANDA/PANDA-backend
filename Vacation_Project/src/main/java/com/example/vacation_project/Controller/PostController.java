package com.example.vacation_project.Controller;

import com.example.vacation_project.Dto.Reqest.PostRequst;
import com.example.vacation_project.Dto.Response.PostIdResponse;
import com.example.vacation_project.Dto.Response.PostListResponse;
import com.example.vacation_project.Service.PostService;
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

    //게시글 저장
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public PostIdResponse savePost(@RequestBody @Valid PostRequst requst,
                                   @PathVariable("accountId") String accountId) {
        return postService.savePost(requst, accountId);
    }

    //공유자 페이지 반환
    @GetMapping
    public PostListResponse postList(@PathVariable("accountId") String accountId, Pageable page) {
        return postService.getPostList(accountId, page);
    }

}
