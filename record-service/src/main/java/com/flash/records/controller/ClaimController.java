package com.flash.records.controller;

import com.flash.records.dao.ClaimRecord;
import com.flash.records.dto.ClaimDTO;
import com.flash.records.dto.SetClaimDTO;
import com.flash.records.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.flash.records.service.*;
import com.flash.records.response.*;

import java.util.List;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-09
 * @Description:
 */


@CrossOrigin
@RestController
@Tag(name = "认领文献", description = "认领文献")
@RequestMapping("/api/claim")
public class ClaimController {
    @Autowired
    private ClaimService claimService;

    @GetMapping("/get/all")
    @Operation(summary = "获取所有认领记录", description = "")
    public CustomResponse getAllClaim() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<ClaimRecord> claimDTOList = claimService.getAllClaim();
            customResponse.setData(claimDTOList);
            customResponse.setMessage("获取成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @GetMapping("/get/{status}")
    @Operation(summary = "获取状态为status认领记录", description = "")
    public CustomResponse getClaimByStatus(@PathVariable Integer status) {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<ClaimRecord> claimDTOList = claimService.getClaimByStatus(status);
            customResponse.setData(claimDTOList);
            customResponse.setMessage("获取成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }


    @PostMapping("/add")
    @Operation(summary = "添加认领记录", description = "提供userid,论文ID的list")
    public CustomResponse addClaim(@RequestBody ClaimDTO claimDTO){
        CustomResponse customResponse = new CustomResponse();
        List<String> documentIds = claimDTO.getDocumentIds();
        Integer uid = claimDTO.getUid();
        try {
            List<ClaimDTO> claimDTOList = claimService.addedClaim(uid,documentIds);
            customResponse.setData(claimDTOList);
            customResponse.setMessage("添加成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }


    @PostMapping("/exam")
    @Operation(summary = "审核认领记录", description = "提供userid，document_id和审核结果")
    public CustomResponse examApply(@RequestBody SetClaimDTO setClaimDTO){//ans:0为待处理，1为通过，2为拒绝
        CustomResponse customResponse = new CustomResponse();
        Integer uid = setClaimDTO.getUid();
        Integer documentId = setClaimDTO.getDocumentId();
        Integer ans = setClaimDTO.getAns();
        if (ans == 1 || ans ==2){
            try {
                claimService.setClaim(uid,documentId,ans);
                customResponse.setMessage("添加成功");
            }catch (BusinessException e){
                customResponse.setCode(e.getCode());
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }
}
