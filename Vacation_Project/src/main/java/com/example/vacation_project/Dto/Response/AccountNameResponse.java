package com.example.vacation_project.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
public class AccountNameResponse {

    @NotBlank
    private String accountId;

    @NotBlank
    private String accountName;

}
