package com.flash.records.controller;


import com.flash.records.dto.ApplicationDTO;
import com.flash.records.dto.SetApplicationDTO;
import com.flash.records.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.flash.records.service.*;
import com.flash.records.response.*;
import com.flash.records.dao.*;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "申请成为学者", description = "申请成为学者")
@RequestMapping("/api/apply")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/add")
    @Operation(summary = "添加申请记录", description = "提供userid")
    public CustomResponse addApply(@RequestBody ApplicationDTO applicationDTO) {
        CustomResponse customResponse = new CustomResponse();
        Integer userId = applicationDTO.getUserId();
        String authorId = applicationDTO.getAuthorId();
        String name = applicationDTO.getName();
        String orName = applicationDTO.getOrName();
        String email = applicationDTO.getEmail();
        String area = applicationDTO.getArea();
        try {
            applicationService.addApply(userId, authorId, name, orName, email, area);
            customResponse.setMessage("添加成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/exam")
    @Operation(summary = "审核申请记录", description = "提供userid和审核结果")
    public CustomResponse examApply(@RequestBody SetApplicationDTO setApplicationDTO) {//ans:0为待处理，1为通过，2为拒绝
        CustomResponse customResponse = new CustomResponse();
        Integer userId = setApplicationDTO.getUserId();
        Integer ans = setApplicationDTO.getAns();
        if (ans == 1 || ans == 2) {
            try {
                applicationService.setApply(userId, ans);
                customResponse.setMessage("添加成功");
            } catch (BusinessException e) {
                customResponse.setCode(e.getCode());
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

    @GetMapping("/info")
    @Operation(summary = "获取申请记录", description = "返回userid、审核状态、记录时间")
    public CustomResponse getApply() {//ans:0为待处理，1为通过，2为拒绝
        CustomResponse customResponse = new CustomResponse();
        try {
            List<ApplicationRecords> applicationRecordsList = applicationService.getApply();
            customResponse.setData(applicationRecordsList);
            customResponse.setMessage("查询成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

}
