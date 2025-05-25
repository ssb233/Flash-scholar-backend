package com.flash.records.controller;


import com.flash.records.dao.History;
import com.flash.records.dto.AddHistoryDTO;
import com.flash.records.dto.GetHistoryDTO;
import com.flash.records.response.CustomResponse;
import com.flash.records.service.HistoryService;
import com.flash.records.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@Tag(name = "历史记录", description = "历史记录相关")
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @PostMapping("/add")
    @Operation(summary = "添加历史记录", description = "提供uid，workId")
    public CustomResponse addHistory(@RequestBody AddHistoryDTO addHistoryDTO){
        CustomResponse customResponse = new CustomResponse();
        Integer uid = addHistoryDTO.getUid();
        String workId = addHistoryDTO.getWorkId();
        String title = addHistoryDTO.getTitle();
        Integer count = addHistoryDTO.getCount();
        String name = addHistoryDTO.getName();
        String publisher = addHistoryDTO.getPublisher();
        Date publishTime = addHistoryDTO.getPublishTime();
        try {
            historyService.addHistory(uid,workId,title,count,name,publisher,publishTime);
            customResponse.setMessage("添加成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/get")
    @Operation(summary = "查询历史记录", description = "提供uid")
    public CustomResponse getHistory(@RequestBody GetHistoryDTO getHistoryDTO){
        CustomResponse customResponse = new CustomResponse();
        Integer uid = getHistoryDTO.getUid();
        try {
            List<History> histories = historyService.getHistory(uid);
            customResponse.setData(histories);
            customResponse.setMessage("查询成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

}
