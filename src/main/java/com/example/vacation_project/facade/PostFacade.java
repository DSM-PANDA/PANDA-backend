package com.example.vacation_project.facade;

import com.example.vacation_project.entity.account.Account;
import com.example.vacation_project.entity.post.Post;
import com.example.vacation_project.entity.post.PostRepository;
import com.example.vacation_project.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PostFacade {

    private final PostRepository postRepository;

    public Post findByPostId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);
    }

    public List<Post> findAllByAccountOrderByIdDesc(Account account, Pageable page) {
        return postRepository.findAllByAccountOrderByIdDesc(account,page);
    }


}
