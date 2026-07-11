package com.example.for_testdemo1.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.for_testdemo1.Common.Result;
import com.example.for_testdemo1.Dto.ArticleCreateDto;
import com.example.for_testdemo1.Entity.ArticleEntity;
import com.example.for_testdemo1.Entity.UserEntity;
import com.example.for_testdemo1.Mapper.ArticleMapper;
import com.example.for_testdemo1.Mapper.UserMapper;
import com.example.for_testdemo1.Service.ArticleService;
import com.example.for_testdemo1.Vo.ArticleCreateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {
    private final UserMapper userMapper;
    public ArticleServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public Result<ArticleCreateVo> createArticle(ArticleCreateDto dto,int userId) {
        ArticleEntity arts = new ArticleEntity();
        UserEntity user =userMapper.selectById(userId);
        BeanUtils.copyProperties(dto,arts);
        save(arts);
        arts = getById(arts.getId());
        ArticleCreateVo vo = new ArticleCreateVo();
        BeanUtils.copyProperties(arts,vo);
        vo.setAuthorName(user.getName());
        return Result.success(vo);
    }

    @Override
    public Result<ArticleEntity>  getArticleInfoById(int id) {
        ArticleEntity articleEntity =getById(id);
        return Result.success(articleEntity);
    }

}
