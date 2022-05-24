package com.example.vacation_project.entity.post;

import com.example.vacation_project.entity.account.Account;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_name", nullable = false, length = 10)
    private String name;

    @Column(name = "post_content", nullable = false, length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
