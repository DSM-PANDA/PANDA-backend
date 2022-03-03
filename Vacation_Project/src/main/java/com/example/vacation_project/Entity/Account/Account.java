package com.example.vacation_project.Entity.Account;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_name", nullable = false,length = 10)
    private String name;


    @Column(name = "account_Id",nullable = false, length = 10)
    private String accountId;

    @Column(name = "account_pwd",nullable = false)
    private String pwd;

}
