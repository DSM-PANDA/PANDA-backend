package com.example.vacation_project.Service;

import com.example.vacation_project.Dto.Reqest.PostRequst;
import com.example.vacation_project.Dto.Response.PostIdResponse;
import com.example.vacation_project.Dto.Response.PostListResponse;
import com.example.vacation_project.Dto.Response.PostViewRespones;
import com.example.vacation_project.Entity.Account.Account;
import com.example.vacation_project.Entity.Account.AccountRepository;
import com.example.vacation_project.Entity.Post.Post;
import com.example.vacation_project.Entity.Post.PostRepository;
import com.example.vacation_project.Exception.NotFoundException;
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

        Long id = postRepository.save(
                Post.builder()
                        .name(requst.getName())
                        .content(requst.getContent())
                        .account(account)
                        .build()
        ).getId();

        return new PostIdResponse(id);
    }
}
