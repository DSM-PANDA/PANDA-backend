package com.example.vacation_project.service;

import com.example.vacation_project.dto.reqest.PostRequst;
import com.example.vacation_project.dto.response.PostIdResponse;
import com.example.vacation_project.dto.response.PostListResponse;
import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.post.Post;
import com.example.vacation_project.entity.post.PostRepository;
import com.example.vacation_project.facade.AccountFacade;
import com.example.vacation_project.facade.PostFacade;
import com.example.vacation_project.service.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final AccountFacade accountFacade;
    private final PostFacade postFacade;
    private final PostUtil postUtil;

    public PostListResponse getPostList(String accountId, Pageable page) {

        Account account = accountFacade.findByAccountId(accountId);

        List<Post> postList = postFacade.findAllByAccountOrderByIdDesc(account, page);
        List<PostListResponse.PostRespones> postRespones = postUtil.getPostList(postList);

        return PostListResponse.builder()
                .accountName(account.getName())
                .postRespones(postRespones)
                .build();

    }

    public PostIdResponse savePost(PostRequst requst, String accountId) {

        return new PostIdResponse(
                postRepository.save(
                    Post.builder()
                        .name(requst.getName())
                        .content(requst.getContent())
                        .account(accountFacade.findByAccountId(accountId))
                        .build()
                ).getId()
        );

    }
}
