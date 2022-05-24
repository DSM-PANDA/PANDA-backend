package com.example.vacation_project.service;

import com.example.vacation_project.dto.reqest.PostRequst;
import com.example.vacation_project.dto.response.PostIdResponse;
import com.example.vacation_project.dto.response.PostListResponse;
import com.example.vacation_project.dto.response.PostViewRespones;
import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.account.AccountRepository;
import com.example.vacation_project.entity.post.Post;
import com.example.vacation_project.entity.post.PostRepository;
import com.example.vacation_project.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    // 쪽지 list 보기
    public PostListResponse getPostList(String accountId, Pageable page) {

        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(NotFoundException::new);

        List<Post> postList = postRepository.findAllByAccountOrderByIdDesc(account, page);
        List<PostViewRespones> postviewResponesList = new ArrayList<>();

        for(Post post : postList) {
            postviewResponesList.add(
                    PostViewRespones.builder()
                            .id(post.getId())
                            .name(post.getName())
                            .build()
            );
        }

        return new PostListResponse(postviewResponesList);

    }

    // 쪽지 생성
    public PostIdResponse savePost(PostRequst requst, String accountId) {

        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(NotFoundException::new);

        return new PostIdResponse(
                postRepository.save(
                    Post.builder()
                        .name(requst.getName())
                        .content(requst.getContent())
                        .account(account)
                        .build()
                ).getId()
        );
    }
}