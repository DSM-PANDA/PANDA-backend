package com.example.vacation_project.dto.reqest;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class AccountReqest {

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    @NotBlank
    @Size(min = 1, max = 10)
    private String accountId;

    @NotBlank
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;\" +\n" + "\"<=>?@＼^_`{|}~]{6,20}$")
    private String password;

}
