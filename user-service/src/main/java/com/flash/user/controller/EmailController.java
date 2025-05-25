package com.flash.user.controller;

import com.flash.user.response.CustomResponse;
import com.flash.user.service.EmailService;
import com.flash.user.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/07/11 20:14
 */
@CrossOrigin
@RestController
@Tag(name = "邮箱")
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/{email}")
    @Operation(summary = "发送验证码邮件")
    @Parameters(value = {
            @Parameter(name = "email", description = "邮箱", in = ParameterIn.PATH, example = "xxx@buaa.edu.cn")
    })
    public CustomResponse sendMail(@PathVariable String email) {
        CustomResponse customResponse = new CustomResponse();
        try {
            emailService.sendActiveCodeEMail(email);
            customResponse.setMessage("发送成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}
