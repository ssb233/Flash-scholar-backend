package com.flash.records.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flash.records.dao.History;
import com.flash.records.mapper.HistoryMapper;
import com.flash.records.service.HistoryService;
import com.flash.records.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;
    @Override
    public void addHistory(Integer uid, String workId,String title, Integer count, String name, String publisher, Date publishTime) throws BusinessException {
        if (uid == null){
            throw new BusinessException(404,"uid不存在");
        }
        if (workId == null){
            throw  new BusinessException(404,"work不存在");
        }
        Date date = new Date();
        System.out.println(title);

        try {
            LambdaQueryWrapper<History> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(History::getUid, uid)
                        .eq(History::getWorkId,workId);// 查询条件：status = 0
            // 查询记录
            History history = historyMapper.selectOne(queryWrapper);
            if (history != null) {
                // 修改字段值
                history.setCount(count);
                history.setTime(date);

                // 更新记录到数据库
                int rows = historyMapper.updateById(history);

                if (rows > 0) {
                    System.out.println("记录更新成功");
                } else {
                    System.out.println("记录更新失败");
                }
            } else {
                History history1 = new History(date,workId,null,uid,title,count,name,publisher,publishTime);
                historyMapper.insert(history1);
            }
        } catch (Exception e) {
            throw new BusinessException(500,"!查询失败: " + e.getMessage());
        }
    }

    @Override
    public List<History> getHistory(Integer uid) throws BusinessException {
        if (uid == null){
            throw new BusinessException(404,"uid不存在");
        }
        try {
            LambdaQueryWrapper<History> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(History::getUid, uid); // 查询条件：status = 0

            // 查询记录
            List<History> histories = historyMapper.selectList(queryWrapper);
            List<History> tmp = new ArrayList<>();
            System.out.println(histories);
            if (histories == null || histories.isEmpty()) {
                return tmp;
            }

            return histories;
        } catch (Exception e) {
            throw new BusinessException(500,"!查询失败: " + e.getMessage());
        }
    }
}
