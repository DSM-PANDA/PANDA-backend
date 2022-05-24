package com.example.vacation_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
public class PostIdResponse {

    @NotBlank
    private Long id;

}
