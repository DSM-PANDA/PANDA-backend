package com.example.vacation_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
public class PostListResponse {

    List<PostViewRespones> postViewResponseList = new ArrayList<>();

}
