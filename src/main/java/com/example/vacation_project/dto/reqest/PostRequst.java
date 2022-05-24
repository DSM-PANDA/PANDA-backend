package com.example.vacation_project.dto.reqest;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostRequst {

    @NotBlank
    @Size(max = 10)
    private String name;

    @NotBlank
    @Size(max = 200)
    private  String content;

}
