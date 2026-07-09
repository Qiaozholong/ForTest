package com.example.for_testdemo1.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("`user`")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private int id;
    private String account;
    private String password;
    private String name;
    private String gender;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
