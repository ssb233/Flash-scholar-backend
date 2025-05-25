package com.flash.records.controller;

import com.flash.records.dao.ApplicationRecords;
import com.flash.records.dao.History;
import com.flash.records.dao.Time;
import com.flash.records.dto.AddHistoryDTO;
import com.flash.records.dto.GetHistoryDTO;
import com.flash.records.dto.UpdateTimeDTO;
import com.flash.records.response.CustomResponse;
import com.flash.records.service.HistoryService;
import com.flash.records.service.TimeService;
import com.flash.records.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@Tag(name = "时间", description = "时间记录相关")
@RequestMapping("/api/time")
public class TimeController {
    @Autowired
    private TimeService timeService;
    @PostMapping("/add")
    @Operation(summary = "更新时间", description = "提供消耗时间")
    public CustomResponse UpdateTime(@RequestBody UpdateTimeDTO updateTimeDTO){
        CustomResponse customResponse = new CustomResponse();
        Integer value = updateTimeDTO.getValue();
        try {
            Date now = new Date();
            System.out.println("当前时间：" + now);

            // 获取当前小时数
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY) + 8;  // 获取当前小时
            System.out.println(currentHour);
            // 计算当前时间段
            int group = getTimeSegment(currentHour);
            System.out.println(group);
            timeService.UpdateTime(value,group);
            customResponse.setMessage("添加成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @GetMapping("/info")
    @Operation(summary = "获取时间", description = "返回时间")
    public CustomResponse getTime() {//ans:0为待处理，1为通过，2为拒绝
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Double> ans = timeService.getTime();
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY) + 8;  // 获取当前小时
            double tmp = currentHour;
            ans.add(tmp);
            customResponse.setData(ans);
            customResponse.setMessage("查询成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    public int getTimeSegment(int currentHour) {
        // 每段时间为 4 小时，共 6 段，0-3, 4-7, 8-11, 12-15, 16-19, 20-23
        if (currentHour >= 0 && currentHour < 4) {
            return 1;  // 00:00 - 03:59
        } else if (currentHour >= 4 && currentHour < 8) {
            return 2;  // 04:00 - 07:59
        } else if (currentHour >= 8 && currentHour < 12) {
            return 3;  // 08:00 - 11:59
        } else if (currentHour >= 12 && currentHour < 16) {
            return 4;  // 12:00 - 15:59
        } else if (currentHour >= 16 && currentHour < 20) {
            return 5;  // 16:00 - 19:59
        } else {
            return 6;  // 20:00 - 23:59
        }
    }

}
