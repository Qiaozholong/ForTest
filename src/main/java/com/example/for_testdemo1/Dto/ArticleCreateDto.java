package com.example.for_testdemo1.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleCreateDto {
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "内容不能为空")
    private String content;
    private int userId;
}
