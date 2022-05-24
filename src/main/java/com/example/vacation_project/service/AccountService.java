package com.example.vacation_project.service;

import com.example.vacation_project.dto.response.AccountNameResponse;
import com.example.vacation_project.dto.response.PostListResponse;
import com.example.vacation_project.dto.response.PostResponse;
import com.example.vacation_project.dto.response.PostViewRespones;
import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.post.Post;
import com.example.vacation_project.entity.post.PostRepository;
import com.example.vacation_project.exception.NotFoundException;
import com.example.vacation_project.service.util.AccountUtil;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountUtil accountUtil;

    private final PostRepository postRepository;

    // 대나무 위의 이름을 반환
    public AccountNameResponse getname() {

        Account account = accountUtil.getAccount();

        return AccountNameResponse.builder()
                .accountId(account.getAccountId())
                .accountName(account.getName())
                .build();

    }

    // 쪽지 상세보기
    public PostResponse getpost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);

        return PostResponse.builder()
                .name(post.getName())
                .content(post.getContent())
                .build();
    }

    // my 페이지 게시글 반환
    public PostListResponse getpostList(Pageable page) {

        Account account = accountUtil.getAccount();

        List<Post> postList = postRepository.findAllByAccountOrderByIdDesc(account,page);
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
