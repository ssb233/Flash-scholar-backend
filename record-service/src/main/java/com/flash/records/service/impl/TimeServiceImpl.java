package com.flash.records.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flash.records.dao.ApplicationRecords;
import com.flash.records.dao.History;
import com.flash.records.dao.Time;
import com.flash.records.mapper.HistoryMapper;
import com.flash.records.mapper.TimeMapper;
import com.flash.records.service.HistoryService;
import com.flash.records.service.TimeService;
import com.flash.records.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private TimeMapper timeMapper;
    @Override
    public void UpdateTime(Integer value,Integer group) throws BusinessException {
        if (value == null){
            throw new BusinessException(404,"value不存在");
        }
        try {
            System.out.println(group);
            System.out.println(value);
            Time timeRecord = timeMapper.selectByGroup(group);
            System.out.println(timeRecord.getValueTime());
            Time timeRecord1 = timeMapper.selectByGroup(0);
            System.out.println(timeRecord1.getValueTime());
            timeMapper.updateStatusByGroup(timeRecord.getCount()+1,timeRecord.getValueTime()+value,group );
            timeMapper.updateStatusByGroup(timeRecord1.getCount()+1, timeRecord1.getValueTime()+value, 0);
        }catch (Exception e){
            throw new BusinessException(404,"fk");
        }
    }

    @Override
    public List<Double> getTime() throws BusinessException {
        List<Time> timeList = timeMapper.selectAll();
        List<Double> ans = new ArrayList<>();
        for (Time time:timeList) {
            if (time.getCount() == 0){
                ans.add(time.getGroupId(),0.0);
            }
            else {
                double value = time.getValueTime();
                double count = time.getCount();
                ans.add(time.getGroupId(),value/count);
            }
        }
        return ans;
    }
}
