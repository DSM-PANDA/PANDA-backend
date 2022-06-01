package com.example.vacation_project.service;

import com.example.vacation_project.dto.response.AccountIdResponse;
import com.example.vacation_project.dto.response.PostListResponse;
import com.example.vacation_project.dto.response.PostResponse;
import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.post.Post;
import com.example.vacation_project.facade.PostFacade;
import com.example.vacation_project.service.util.AccountUtil;
import com.example.vacation_project.service.util.PostUtil;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountUtil accountUtil;
    private final PostFacade postFacade;
    private final PostUtil postUtil;

    public AccountIdResponse getAccountId() {
        return new AccountIdResponse(accountUtil.getAccountId());
    }

    public PostResponse getpost(Long postId) {

        Post post = postFacade.findByPostId(postId);

        return PostResponse.builder()
                .name(post.getName())
                .content(post.getContent())
                .build();
    }

    public PostListResponse getpostList(Pageable page) {

        Account account = accountUtil.getAccount();

        List<Post> postList = postFacade.findAllByAccountOrderByIdDesc(account, page);
        List<PostListResponse.PostRespones> postRespones = postUtil.getPostList(postList);

        return PostListResponse.builder()
                .accountName(account.getName())
                .postRespones(postRespones)
                .build();

    }


}
