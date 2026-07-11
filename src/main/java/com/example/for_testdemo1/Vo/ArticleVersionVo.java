package com.example.for_testdemo1.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleVersionVo {
    private int id;
    private String title;
    private LocalDateTime createTime;
}
