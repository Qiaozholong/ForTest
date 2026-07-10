package com.example.for_testdemo1.Util;

import com.example.for_testdemo1.Common.BusinessException;
import com.example.for_testdemo1.Entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

public class ForUser {
    //查询jwt令牌中的user,role字段判断权限
    public static void checkPermission(HttpServletRequest request, @PathVariable int id) {
        int userId = (int) request.getAttribute("userId");
        int userRole = (int) request.getAttribute("role");
        if (id != userId && userRole != 1) {
            throw new BusinessException(403, "无权访问该数据信息");
        }
    }
    //同上，但只有role字段
    public static void checkRole(HttpServletRequest request) {
        int userRole = (int) request.getAttribute("role");
        if (userRole != 1) {
            throw new BusinessException(402, "无权访问该数据信息");
        }
    }
    public static <T> List<T> convertList(List<UserEntity> entities,Class<T> targetClass) {
    return entities.stream()
            .map(element->{
                try{
                    T t=targetClass.getDeclaredConstructor().newInstance();
                    BeanUtils.copyProperties(element,t);
                    return t;
                }catch(Exception e){
                    throw  new RuntimeException(e);
                }
            }).collect(Collectors.toList());
    }
}
