package com.example.vacation_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class PostListResponse {

    private final String accountName;

    List<PostRespones> postViewResponseList;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class PostRespones {

        private final Long id;

        private final String name;

    }
}
