package com.example.for_testdemo1.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleCreateVo {
    private int id;
    private int userId;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String authorName;
}
