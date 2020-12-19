package com.cyh.controller;

import com.cyh.bean.UserInfo;
import com.cyh.bean.msg;
import com.cyh.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/login.do")
    public msg verifyUser(@RequestBody UserInfo userInfo) {
        if (userInfoService.verifyUser(userInfo).size()>0) {
            return msg.success().add("id",userInfoService.verifyUser(userInfo).get(0).getId());
        } else {
            return msg.fail().add("info", "用户名或密码错误");
        }
    }

    @RequestMapping("/register.do")
    public msg registerUser(@RequestBody UserInfo userInfo) {
        if (userInfoService.registerUser(userInfo)) {
            return msg.success().add("info", "注册成功");
        } else {
            return msg.fail().add("info", "用户名已存在");
        }
    }

    @GetMapping("/users.do")
    public msg getone(Integer id) {
        UserInfo user = userInfoService.getone(id);
        return msg.success().add("user",user);
    }

    @PostMapping("/users.do")
    public msg updateUser(@RequestBody UserInfo userInfo){
        if (userInfoService.updateUser(userInfo)){
            return msg.success().add("info", "用户信息更新成功");
        }
        else{
            return msg.fail().add("info","用户信息更新失败");
        }
    }
}
