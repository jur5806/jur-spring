package com.jurspring.jt.controller;

import com.jurspring.jt.home.User;
import com.jurspring.jt.result.Result;
import com.jurspring.jt.result.ResultFactory;
import com.jurspring.jt.service.AdminUserRoleService;
import com.jurspring.jt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @GetMapping("/march/admin/user")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }

    @PutMapping("/march/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    @PostMapping("/march/admin/password")
    public Result resetPassword(@RequestBody @Valid User requestUser) {
        log.info("----"+requestUser);
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    @PostMapping("/march/admin/user")
    public Result editUser(@RequestBody @Valid User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }

    @GetMapping("/march/admin/userDetail")
    public Result userDetail(String username) {
        return ResultFactory.buildSuccessResult(userService.findByUsername(username));
    }

    @GetMapping("/march/admin/deleUser")
    public Result UserDele(Integer id) {
        userService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }

}