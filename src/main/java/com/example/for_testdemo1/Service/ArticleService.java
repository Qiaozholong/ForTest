package com.example.for_testdemo1.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.for_testdemo1.Common.Result;
import com.example.for_testdemo1.Dto.ArticleCreateDto;
import com.example.for_testdemo1.Entity.ArticleEntity;
import com.example.for_testdemo1.Vo.ArticleCreateVo;

public interface ArticleService extends IService<ArticleEntity> {
    //article创建入口
    Result<ArticleCreateVo> createArticle(ArticleCreateDto dto,int userId);
    //article信息查询入口
    Result<ArticleEntity> getArticleInfoById(int id);
}
