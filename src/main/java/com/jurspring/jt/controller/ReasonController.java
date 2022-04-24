package com.jurspring.jt.controller;

import com.jurspring.jt.home.Integeral;
import com.jurspring.jt.home.Reason;
import com.jurspring.jt.result.Result;
import com.jurspring.jt.result.ResultFactory;
import com.jurspring.jt.service.ReasonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class ReasonController {
    @Autowired
    ReasonService reasonService;

    @GetMapping("/march/reasonList")
    public Result reasonList() {
        return ResultFactory.buildSuccessResult(reasonService.findAllList());
    }

    @PostMapping("/march/reasonUpdata")
    public Result reasonUpdata(@RequestBody @Valid Reason reason) {
        reasonService.updata(reason);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    @GetMapping("/march/deleReason")
    public Result deleReason(Integer id) {
        reasonService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
