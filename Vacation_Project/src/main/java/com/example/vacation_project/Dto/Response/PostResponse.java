package com.example.vacation_project.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String content;

}