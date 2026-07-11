package com.example.for_testdemo1.Controller;

import com.example.for_testdemo1.Common.Result;
import com.example.for_testdemo1.Dto.ArticleCreateDto;
import com.example.for_testdemo1.Entity.ArticleEntity;
import com.example.for_testdemo1.Service.ArticleService;
import com.example.for_testdemo1.Vo.ArticleCreateVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/getInfo/{id}")
    public Result<ArticleEntity> getArticleInfo(@PathVariable int id) {
        return articleService.getArticleInfoById(id);
    }

    @PostMapping("/createArticle")
    public Result<ArticleCreateVo> createArticle(@Valid @RequestBody ArticleCreateDto dto, HttpServletRequest requset) {
        int userId = (int)requset.getAttribute("userId");
        dto.setUserId(userId);
        return articleService.createArticle(dto,userId);
    }

}
