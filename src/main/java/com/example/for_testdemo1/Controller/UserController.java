package com.example.for_testdemo1.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.for_testdemo1.Common.BusinessException;
import com.example.for_testdemo1.Common.Result;
import com.example.for_testdemo1.Dto.LoginDto;
import com.example.for_testdemo1.Dto.UserDto;
import com.example.for_testdemo1.Dto.RegisterDto;
import com.example.for_testdemo1.Dto.UserResetDto;
import com.example.for_testdemo1.Entity.UserEntity;
import com.example.for_testdemo1.Service.UserService;
import com.example.for_testdemo1.Vo.LoginResultVo;
import com.example.for_testdemo1.Vo.UserDetailVo;
import com.example.for_testdemo1.Vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    //构造方法注入
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //注册接口
    @PostMapping("/register")
    public Result<LoginResultVo> userregister(@Valid @RequestBody RegisterDto dto) {
        return userService.userRegister(dto);
    }

    //登录接口
    @PostMapping("/login")
    public Result<LoginResultVo> UserLoginVo(@Valid@RequestBody LoginDto dto) {
        return userService.userLogin(dto);
    }

    //简要查询接口
    @GetMapping("/getall")
    public Result<List<UserVo>> getAll() {
        return userService.userInfo();
    }

    //单独查询用户简要信息接口
    @GetMapping("/{id}")
    public Result<UserVo> getUserInfo(@PathVariable int id, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        int role = (int) request.getAttribute("role");
        if (role != 1) {
            if (id != userId) {
                throw new BusinessException(403,"无权访问该数据信息");
            }
        }
        return userService.userInfo(id);
    }

    //单独查询用户详细信息接口
    @GetMapping("/get/{id}")
    public Result<UserEntity> getUserAllInfo(@PathVariable int id, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        int role = (int) request.getAttribute("role");
        if (role != 1) {
            if (id != userId) {
                throw new BusinessException(403,"无权访问该数据信息");
            }
        }
        return userService.userAllInfo(id);
    }

    @GetMapping("/get")
    public Result<List<UserDetailVo>> getUserAllInfo() {
        return userService.userAllInfo();
    }

    //分页接口
    @GetMapping("/page")
    public Result<Page<UserVo>> Page(@RequestParam(defaultValue = "1") int current) {
        return userService.page(current);
    }

    //模糊查询接口
    @GetMapping("/search")
    public Result<List<UserDto>> userSearch(
            @RequestParam String search) {
        return userService.userSearch(search);
    }

    //重置密码接口
    @PatchMapping("/reset/{id}")
    public Result<UserResetDto> resetPassword(
            @Valid @RequestBody UserResetDto Ur,
            @PathVariable int id,
            HttpServletRequest request
    ) {
        int  userId = (int) request.getAttribute("userId");
        if ( id!= userId) {
            throw new BusinessException(403,"无权访问该数据信息");
        }
        return userService.ResetPassword(Ur);
    }

    //删除账户接口
    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteUser(
            @PathVariable int id,
            HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        if (id != userId) {
            throw new BusinessException(403,"无权访问该数据信息");
        }
        return userService.deleteUser(id);
    }


    //字符返回测试接口
    @GetMapping("/Test/{msg}")
    public Result<String> test(@PathVariable String msg) {
        return Result.unauthorized(msg);
    }

}
