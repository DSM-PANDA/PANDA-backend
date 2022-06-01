package com.example.vacation_project.service.util;

import com.example.vacation_project.dto.response.PostListResponse;
import com.example.vacation_project.entity.post.Post;

import java.util.ArrayList;
import java.util.List;

public class PostUtil {

    public List<PostListResponse.PostRespones> getPostList(List<Post> postList) {
        List<PostListResponse.PostRespones> postRespones = new ArrayList<>();

        for(Post post : postList) {
            postRespones.add(
                    PostListResponse.PostRespones.builder()
                            .id(post.getId())
                            .name(post.getName())
                            .build()
            );
        }

        return postRespones;
    }

}
