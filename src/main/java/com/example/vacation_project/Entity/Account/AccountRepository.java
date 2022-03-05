package com.example.vacation_project.Entity.Account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);

}
