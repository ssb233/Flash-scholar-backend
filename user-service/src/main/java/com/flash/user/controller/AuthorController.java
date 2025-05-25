package com.flash.user.controller;

import com.flash.user.dao.Author;
import com.flash.user.dto.UserDTO;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.AuthorService;
import com.flash.user.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author yury
 * @description: TODO
 */
@CrossOrigin
@RestController
@Tag(name = "作者")
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/num")
    @Operation(summary = "作者个人门户个数", description = "")
    public CustomResponse getNumOfUser() {
        CustomResponse customResponse = new CustomResponse();
        Long num;
        try {
            num = authorService.getNumOfAuthor();
            customResponse.setData(num);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/random/{num}")
    @Operation(summary = "获取随机num个作者信息", description = "随机获取用户信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "作者id", in = ParameterIn.PATH, example = "A1")
    })
    public CustomResponse getRandomAuthor(@PathVariable Long num) {
        CustomResponse customResponse = new CustomResponse();
        try {
            Page<Author> author = authorService.findRandomly(num);
            customResponse.setData(author);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/id/{id}")
    @Operation(summary = "获取一个作者信息", description = "由id获取用户信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "作者id", in = ParameterIn.PATH, example = "A1")
    })
    public CustomResponse getAuthorById(@PathVariable String id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            Optional<Author> author = authorService.findById(id);
            customResponse.setData(author);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/name/{name}")
    @Operation(summary = "获取作者信息", description = "由name获取用户信息")
    @Parameters(value = {
            @Parameter(name = "name", description = "作者name", in = ParameterIn.PATH, example = "M Pomin")
    })
    public CustomResponse getAuthorByName(@PathVariable String name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Author> author = authorService.findByName(name);
            customResponse.setData(author);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}
