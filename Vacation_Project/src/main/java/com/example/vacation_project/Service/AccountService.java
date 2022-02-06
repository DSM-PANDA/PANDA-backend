package com.example.vacation_project.Service;

import com.example.vacation_project.Dto.Response.AccountNameResponse;
import com.example.vacation_project.Dto.Response.PostListResponse;
import com.example.vacation_project.Dto.Response.PostResponse;
import com.example.vacation_project.Dto.Response.PostViewResponse;
import com.example.vacation_project.Entity.Account.Account;
import com.example.vacation_project.Entity.Post.Post;
import com.example.vacation_project.Entity.Post.PostRepository;
import com.example.vacation_project.Exception.NotFoundException;
import com.example.vacation_project.Service.Util.AccountUtil;
import com.example.vacation_project.Service.Util.AuthenticationUtil;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AuthenticationUtil authenticationUtil;
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

    // 쪽지 하나의 name과 내용을 보여줌
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
        List<PostViewResponse> postViewResponseList = new ArrayList<>();

        for(Post post : postList) {
            postViewResponseList.add(
                    PostViewResponse.builder()
                            .id(post.getId())
                            .name(post.getName())
                            .build()
            );
        }

        return PostListResponse.builder()
                .postViewResponseList(postViewResponseList)
                .build();

    }


}
