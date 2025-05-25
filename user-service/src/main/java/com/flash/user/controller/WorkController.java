package com.flash.user.controller;

import com.flash.user.dao.Author;
import com.flash.user.dao.Work;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.AuthorService;
import com.flash.user.service.WorkService;
import com.flash.user.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author yury
 * @description: TODO
 */
@CrossOrigin
@RestController
@Tag(name = "成果")
@RequestMapping("/api/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping("/num")
    @Operation(summary = "数据库成果个数", description = "")
    public CustomResponse getNumOfWork() {
        CustomResponse customResponse = new CustomResponse();
        Long num;
        try {
            num = workService.getNumOfWork();
            customResponse.setData(num);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/id/{id}")
    @Operation(summary = "获取一个成果信息", description = "由id获取成果信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "成果id", in = ParameterIn.PATH, example = "W2006489392")
    })
    public CustomResponse getWorkById(@PathVariable String id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            Optional<Work> work = workService.findByWorkId(id);
            customResponse.setData(work);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

//    @GetMapping("/title")
//    @Operation(summary = "获取一个成果信息", description = "由title获取成果信息")
//    @Parameters(value = {
//            @Parameter(name = "title", description = "标题", example = "Molecular polarizability and conformation of dithioacetals")
//    })
//    public CustomResponse getWorkByTitle(@RequestParam String title) {
//        CustomResponse customResponse = new CustomResponse();
//        try {
//            List<Work> workList = workService.findByTitle(title);
//            customResponse.setData(workList);
//        } catch (BusinessException e) {
//            customResponse.setCode(e.getCode());
//            customResponse.setMessage(e.getMessage());
//        }
//        return customResponse;
//    }
}
