package com.flash.achievements.controller;

import com.flash.achievements.dao.Work;
import com.flash.achievements.dto.*;
import com.flash.achievements.response.CustomResponse;
import com.flash.achievements.service.WorkService;
import com.flash.achievements.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 11:24
 */

@CrossOrigin
@RestController
@Tag(name = "论文", description = "论文管理")
@RequestMapping("/api/works")
public class WorkController {
    @Autowired
    private WorkService workService;

    /**
     * 添加论文信息
     */
    @PostMapping("/add")
    @Operation(summary = "添加论文信息", description = "提供论文相关属性")
    public CustomResponse addDocument(@RequestBody DocumentAdd documentAdd){
        CustomResponse customResponse = new CustomResponse();
        try {
            Work addeddocument = workService.addDocument(documentAdd);
            customResponse.setData(addeddocument);
            customResponse.setMessage("添加成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 查询所有论文信息
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有论文", description = "返回所有的论文")
    public CustomResponse getAllDocuments() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Work> works = workService.getAllDocuments();
            customResponse.setData(works);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 根据论文ID查询获奖信息
     */
    @GetMapping("/{paperId}")
    @Operation(summary = "根据论文ID查询论文信息", description = "返回指定论文ID的论文记录")
    @Parameter(name = "paperId", description = "论文ID", in = ParameterIn.PATH, example = "W001")
    public CustomResponse getPaperByPaperId(@PathVariable String paperId) {
        CustomResponse customResponse = new CustomResponse();
        try {
            DocumentDTO documentDTO = workService.getDocumentById(paperId);
            customResponse.setData(documentDTO);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }


    /**
     * 删除论文信息
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除论文信息", description = "通过论文 ID 删除指定的论文信息")
    @Parameter(name = "id", description = "论文 ID", in = ParameterIn.PATH, example = "1")
    public CustomResponse deleteAward(@PathVariable Integer id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            workService.deleteDocument(id);
            customResponse.setMessage("删除成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 根据关键字搜索论文信息
     */
    @PostMapping("/search")
    @Operation(summary = "根据题目搜索论文信息", description = "提供搜索关键字")
    public CustomResponse searchDocument(@RequestParam String keyWords){
        CustomResponse customResponse = new CustomResponse();
        try {
            List<DocumentDTO> documentDTOS = workService.searchDocument(keyWords);
            customResponse.setData(documentDTOS);
            customResponse.setMessage("搜索成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }


    /**
     * 根据论文id查询此论文引用了什么论文
     */
    @GetMapping("/reference/{paperId}")
    @Operation(summary = "根据论文id查询此论文引用了哪些论文", description = "提供论文id")
    @Parameter(name = "paperId", description = "论文ID", in = ParameterIn.PATH, example = "1")
    public CustomResponse getReference(@PathVariable Integer paperId){
        CustomResponse customResponse = new CustomResponse();
        try {
            List<DocumentDTO> documentDTOS = workService.getReference(paperId);
            customResponse.setData(documentDTOS);
            customResponse.setMessage("查找成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 根据论文id查询引用了此论文的论文信息
     */
    @GetMapping("/referenced/{paperId}")
    @Operation(summary = "根据论文id查询引用了此论文的论文信息", description = "提供论文id")
    @Parameter(name = "paperId", description = "论文ID", in = ParameterIn.PATH, example = "1")
    public CustomResponse getReferenced(@PathVariable Integer paperId){
        CustomResponse customResponse = new CustomResponse();
        try {
            List<DocumentDTO> documentDTOS = workService.getReferenced(paperId);
            customResponse.setData(documentDTOS);
            customResponse.setMessage("查找成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

