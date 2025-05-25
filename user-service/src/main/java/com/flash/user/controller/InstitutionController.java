package com.flash.user.controller;

import com.flash.user.dao.Institution;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.InstitutionService;
import com.flash.user.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author yury
 * @description: TODO
 */
@CrossOrigin
@RestController
@Tag(name = "机构")
@RequestMapping("/api/institution")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @GetMapping("/num")
    @Operation(summary = "机构个数", description = "")
    public CustomResponse getNumOfInstitution() {
        CustomResponse customResponse = new CustomResponse();
        Long num;
        try {
            num = institutionService.getInstitutionCount();
            customResponse.setData(num);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/id/{id}")
    @Operation(summary = "获取组织，通过id")
    public CustomResponse getInstitutionByOid(@PathVariable String id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            Optional<Institution> institution = institutionService.findById(id);
            customResponse.setData(institution);
            customResponse.setMessage("获取成功");
        } catch (BusinessException e) {
            // e.printStackTrace();
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
            return customResponse;
        }
        return customResponse;
    }

    @PostMapping("/rank/worksCount")
    @Operation(summary = "获取机构成果个数排行")
    public CustomResponse getRankByWorksCount() {
        CustomResponse customResponse = new CustomResponse();
        Page<Institution> institution = institutionService.findAllByOrderByWorksCountDesc();
        customResponse.setData(institution);
        customResponse.setMessage("获取成功");
        return customResponse;
    }

    @PostMapping("/rank/citedByCount")
    @Operation(summary = "获取机构总引用量排行")
    public CustomResponse getRankByCitedByCount() {
        CustomResponse customResponse = new CustomResponse();
        Page<Institution> institution = institutionService.findAllByOrderByCitedByCountDesc();
        customResponse.setData(institution);
        customResponse.setMessage("获取成功");
        return customResponse;
    }

}
