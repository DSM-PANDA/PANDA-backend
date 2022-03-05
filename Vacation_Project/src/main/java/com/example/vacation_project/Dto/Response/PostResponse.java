package com.example.vacation_project.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
public class PostResponse {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String content;

}