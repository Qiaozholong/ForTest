package com.example.for_testdemo1.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserResetDto {
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "新密码不能为空")
    private String newone;
}
