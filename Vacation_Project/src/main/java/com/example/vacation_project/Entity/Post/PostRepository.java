package com.example.vacation_project.Entity.Post;

import com.example.vacation_project.Entity.Account.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAllByAccountOrderByIdDesc(Account account, Pageable page);

}
