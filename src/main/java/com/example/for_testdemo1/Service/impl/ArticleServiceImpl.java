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
import com.example.for_testdemo1.Vo.ArticleVersionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {
    private final UserMapper userMapper;
    public ArticleServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //创建文件入口
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
    //简单查询对应id文章
    @Override
    public Result<ArticleEntity>  getArticleInfoById(int id) {
        ArticleEntity articleEntity =getById(id);
        return Result.success(articleEntity);
    }
    //查询对应用户id名下文章
    @Override
    public Result<List<ArticleVersionVo>> showArticle(int id) {
        List<ArticleEntity> AE =lambdaQuery()
                .eq(ArticleEntity::getUserId,id)
                .list();
        List<ArticleVersionVo> vos =AE.stream()
                .map(e->{
                        ArticleVersionVo vo = new ArticleVersionVo();
                        BeanUtils.copyProperties(e,vo);
                        return vo;
                }).collect(Collectors.toList());
        return Result.success(vos);
    }

}
