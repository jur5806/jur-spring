package com.jurspring.jt.controller;

import com.jurspring.jt.home.PointVeiw;
import com.jurspring.jt.home.Resumeinfo;
import com.jurspring.jt.result.Result;
import com.jurspring.jt.result.ResultFactory;
import com.jurspring.jt.service.PointVeiwService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class PointVeiwController {
    @Autowired
    PointVeiwService pointVeiwService;

    @GetMapping("/march/pointsList")
    public Result pointsList() {
        log.info("----查询所有积分列表----");
        return ResultFactory.buildSuccessResult(pointVeiwService.list());
    }

    @GetMapping("/march/pointsIdList")
    public Result pointsIdList(Integer userId) {
        return ResultFactory.buildSuccessResult(pointVeiwService.findAllByuserId(userId));
    }

    @PostMapping("/march/pointsInfoAdd")
    public Result pointsInfoAdd(@RequestBody @Valid PointVeiw pointVeiw) {
        int status = pointVeiwService.add(pointVeiw);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("该账户余额不足");
            case 1:
                return ResultFactory.buildSuccessResult("修改成功");
//            case 2:
//                return ResultFactory.buildFailResult("修改成功");
        }
        return ResultFactory.buildFailResult("未知错误");
    }


}
