package com.jurspring.jt.controller;

import com.jurspring.jt.home.Book;
import com.jurspring.jt.home.Recruit;
import com.jurspring.jt.home.User;
import com.jurspring.jt.result.Result;
import com.jurspring.jt.result.ResultFactory;
import com.jurspring.jt.service.RecruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class RecruitController {
    @Autowired
    RecruitService recruitService;

    @GetMapping("/march/recruitList")
    public Result listRecruit() {
        log.info("====8888====");
        return ResultFactory.buildSuccessResult(recruitService.list());
    }

    @PostMapping("/march/recruitAdd")
    public Result recruitAdd(@RequestBody @Valid Recruit recruit) {
        log.info("===85858==");
        recruitService.addOrUpdate(recruit);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    @GetMapping("/march/deleRecruit")
    public Result RecruitDele(Integer recruitId) {
        recruitService.deleteById(recruitId);
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @PutMapping("/march/updateRecruit")
    public Result RecruitUpadte(@RequestBody @Valid Recruit recruit) {
        log.info("===职位已过期==");
        recruitService.updateStatus(recruit);
        return ResultFactory.buildSuccessResult("状态更新成功");
    }

}
