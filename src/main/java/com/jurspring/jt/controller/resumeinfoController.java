package com.jurspring.jt.controller;

import com.jurspring.jt.home.Recruit;
import com.jurspring.jt.home.Resumeinfo;
import com.jurspring.jt.result.Result;
import com.jurspring.jt.result.ResultFactory;
import com.jurspring.jt.service.ResumeInfoService;
import com.jurspring.jt.util.DateUtilJava8;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class resumeinfoController {
    @Autowired
    ResumeInfoService resumeInfoService;

    @GetMapping("/march/resumeInfoList")
    public Result resumeinfoList() {
        log.info("====99999===="+ DateUtilJava8.getNow());
        return ResultFactory.buildSuccessResult(resumeInfoService.list());
    }

    @PostMapping("/march/resumeInfoAdd")
    public Result resumeInforecruitAdd(@RequestBody @Valid Resumeinfo resumeinfo) {
        resumeInfoService.addOrUpdate(resumeinfo);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    @GetMapping("/march/deleResumeInfo")
    public Result ResumeInfoDele(Integer resumeInfoId) {
        resumeInfoService.deleteById(resumeInfoId);
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @PutMapping("/march/updateResumeInfoApprovalState")
    public Result ResumeInfoApprovalState(@RequestBody @Valid Resumeinfo resumeinfo) {
        log.info("===加入人才库==");
        resumeInfoService.updateApprovalState(resumeinfo);
        return ResultFactory.buildSuccessResult("状态更新成功");
    }
    @GetMapping("/march/getResumeInfoByUserId")
    public Result getResumeInfoByUserId(Integer userId) {
        return ResultFactory.buildSuccessResult(resumeInfoService.findAllByuserId(userId));
    }

    @GetMapping("/march/getByhrIdResumeList")
    public Result getByhrIdList(Integer hrId,Integer recruitId) {
        return ResultFactory.buildSuccessResult(resumeInfoService.getByHrId(hrId,recruitId));
    }

}
