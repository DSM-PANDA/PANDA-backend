package com.example.vacation_project.entity.post;

import com.example.vacation_project.entity.account.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAllByAccountOrderByIdDesc(Account account, Pageable page);

}
