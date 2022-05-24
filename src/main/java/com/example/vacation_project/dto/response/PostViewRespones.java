package com.example.vacation_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
public class PostViewRespones {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

}
