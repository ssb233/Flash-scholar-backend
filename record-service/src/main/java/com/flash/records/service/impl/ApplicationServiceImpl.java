package com.flash.records.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.flash.records.dao.ApplicationRecords;
import com.flash.records.dao.User;
import com.flash.records.mapper.ApplicationMapper;
import com.flash.records.mapper.UserMapper;
import com.flash.records.service.ApplicationService;
import com.flash.records.service.MsgService;
import com.flash.records.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private MsgService msgService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addApply(Integer userId, String authorId, String name, String orName, String email, String area) throws BusinessException {
        if (userId == null) {
            throw new BusinessException(400, "用户不存在");
        }
        Date now = new Date();
        try {
            ApplicationRecords applicationRecords = new ApplicationRecords(null, userId, 1, now, 0, authorId, name, orName, email, area);
            applicationMapper.insert(applicationRecords);
        } catch (Exception e) {
            throw new BusinessException(404, e.getMessage());
        }
    }

    @Override
    public void setApply(Integer userId, Integer status) throws BusinessException {
        if (userId == null) {
            throw new BusinessException(400, "用户不存在");
        }
        if (status == null) {
            throw new BusinessException(400, "状态值不能为空");
        }
        System.out.println(userId);
        System.out.println(status);
        try {
//            System.out.println("UserID: " + userID); // 打印 userID

            // 查询 application_records 表中是否有对应的记录
            List<ApplicationRecords> applicationRecords1 = applicationMapper.selectByUid(userId);
            System.out.println(applicationRecords1);
            ApplicationRecords applicationRecords = applicationRecords1.get(applicationRecords1.size()-1);
            System.out.println(applicationRecords.getAuthorId());
            if (applicationRecords == null) {
                System.out.println("不存在");
            }

//            System.out.println("Application Record: " + applicationRecord); // 打印查询结果

            // 将更新后的记录存入数据库
            int rowsAffected = applicationMapper.updateStatusByUserId(userId, status);
//            System.out.println("Rows Affected: " + rowsAffected); // 打印更新受影响的行数
            // 检查更新是否成功
            if (rowsAffected == 0) {
                System.out.println("更新失败");
            }

            String authorId = applicationRecords.getAuthorId();
            // 发送认领消息
            if (status == 1) {
                // 覆盖认证过该authouId的user
                UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("author_id", authorId).set("author_id", null);
                userMapper.update(null, updateWrapper);

                msgService.sendMsg(userId, "您的个人门户认领申请受管理员审批通过。", applicationRecords.getAuthorId(), 0);
                // 通过后设置 user 的role 为1
                updateWrapper = new UpdateWrapper<>();

                updateWrapper.eq("uid", userId).set("role", 1).set("author_id", applicationRecords.getAuthorId());
                userMapper.update(null, updateWrapper);
            } else {
                msgService.sendMsg(userId, "您的个人门户认领申请受管理员审批未通过。", null, 0);
            }

        } catch (Exception e) {
            // 捕获异常并包装成 BusinessException 抛出
            throw new BusinessException(500, "系统异常: " + e.getMessage());
        }
    }

    @Override
    public List<ApplicationRecords> getApply() throws BusinessException {
        try {
            // 使用 MyBatis-Plus 的 LambdaQueryWrapper 查询 status = 0 的记录
            LambdaQueryWrapper<ApplicationRecords> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ApplicationRecords::getStatus, 0); // 查询条件：status = 0
            System.out.println(queryWrapper);
            System.out.println("???");
            // 查询记录
            List<ApplicationRecords> records = applicationMapper.selectList(queryWrapper);
            List<ApplicationRecords> list = new ArrayList<>();
            if (records == null || records.isEmpty()) {
                return list;
            }

            return records;
        } catch (Exception e) {
            throw new BusinessException(500, "查询 application_records 失败: " + e.getMessage());
        }
    }
}
