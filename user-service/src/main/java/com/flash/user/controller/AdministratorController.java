package com.flash.user.controller;

import com.flash.user.dto.ApplicationRequestDTO;
import com.flash.user.dto.ClaimRequestDTO;
import com.flash.user.dto.LoginDTO;
import com.flash.user.dto.UserDTO;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.AdministratorService;
import com.flash.user.service.UserService;
import com.flash.user.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yury
 * @description: TODO
 */
@CrossOrigin
@RestController
@Tag(name = "管理员")
@RequestMapping("/api/admin")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public CustomResponse loginAdmin(@RequestBody LoginDTO loginDTO) {
        CustomResponse customResponse = new CustomResponse();
        try {
            System.out.println(-1);
            String email = loginDTO.getEmail();
            String password = loginDTO.getPassword();
            System.out.println(0);
            UserDTO userDTO = administratorService.loginAdmin(email, password);
            customResponse.setData(userDTO);
            customResponse.setMessage("登录成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/role/uid/{uid}")
    @Operation(summary = "根据uid更改用户role")
    public CustomResponse updateUserRoleByUid(@PathVariable int uid, @RequestParam int role) {
        CustomResponse customResponse = new CustomResponse();
        try {
            administratorService.updateUserRoleByUid(uid, role);
            customResponse.setData(true);
            customResponse.setMessage("更改成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/claim")
    @Operation(summary = "处理claim")
    public CustomResponse processClaim(@RequestBody ClaimRequestDTO requestDTO){
        CustomResponse customResponse = new CustomResponse();
        Integer claimId = requestDTO.getClaimId();
        Integer adminId = requestDTO.getAdminId();
        Integer status = requestDTO.getStatus();
        try {
            administratorService.processClaim(claimId, adminId, status);
            customResponse.setData(true);
            customResponse.setMessage("处理成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    public CustomResponse processApplication(@RequestBody ApplicationRequestDTO requestDTO){
        CustomResponse customResponse = new CustomResponse();
        Integer applicationId = requestDTO.getApplicationId();
        Integer adminId = requestDTO.getAdminId();
        Integer status = requestDTO.getStatus();
        try {
            administratorService.processApplication(adminId, applicationId, status);
            customResponse.setData(true);
            customResponse.setMessage("处理成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }

        return customResponse;
    }
}
