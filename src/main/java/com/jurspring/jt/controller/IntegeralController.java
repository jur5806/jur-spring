package com.jurspring.jt.controller;

import com.jurspring.jt.home.Integeral;
import com.jurspring.jt.home.PointVeiw;
import com.jurspring.jt.result.Result;
import com.jurspring.jt.result.ResultFactory;
import com.jurspring.jt.service.IntegeralService;
import com.jurspring.jt.util.DateUtilJava8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class IntegeralController {

    @Autowired
    IntegeralService integeralService;
    @GetMapping("/march/integeralList")
    public Result integeralList() {
        return ResultFactory.buildSuccessResult(integeralService.list());
    }

    @PostMapping("/march/integeralUpdata")
    public Result integeralUpdata(@RequestBody @Valid Integeral integeral) {
        integeralService.updateIntegeralSet(integeral);
        return ResultFactory.buildSuccessResult("修改成功");
    }
}
