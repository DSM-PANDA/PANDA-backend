package com.example.vacation_project.service;

import com.example.vacation_project.dto.response.AccountNameResponse;
import com.example.vacation_project.dto.response.PostListResponse;
import com.example.vacation_project.dto.response.PostResponse;
import com.example.vacation_project.dto.response.PostViewRespones;
import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.post.Post;
import com.example.vacation_project.facade.PostFacade;
import com.example.vacation_project.exception.NotFoundException;
import com.example.vacation_project.service.util.AccountUtil;
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
        List<PostViewRespones> postViewResponseList = new ArrayList<>();

        for(Post post : postList) {
            postViewResponseList.add(
                    PostViewRespones.builder()
                            .id(post.getId())
                            .name(post.getName())
                            .build()
            );
        }

        return new PostListResponse(postViewResponseList);

    }


}
