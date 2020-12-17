package com.jurspring.jt.controller;

import com.jurspring.jt.home.User;
import com.jurspring.jt.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.HtmlEscapeTag;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@RestController
public class LoginController {

    @CrossOrigin
    @PostMapping(value = "march/login")
    @ResponseBody
    public Result login(@RequestBody User requstUser) {
        // 对html标签进行转义，防止xs攻击
        String username = requstUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        if(!Objects.equals("admin", username) || !Objects.equals("123456", requstUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return  new Result(400);
        } else {
            System.out.println("success");
            return new Result(200);
        }

    }
}
