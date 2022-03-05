package com.example.vacation_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JwtToken {

    private final String accessToken;

    private final String refreshToken;

}
