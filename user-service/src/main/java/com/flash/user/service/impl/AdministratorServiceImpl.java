package com.flash.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.flash.user.converter.UserConverter;
import com.flash.user.dao.User;
import com.flash.user.dto.UserDTO;
import com.flash.user.mapper.UserMapper;
import com.flash.user.service.AdministratorService;
import com.flash.user.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author yury
 * @description: TODO
 */
@Slf4j
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDTO loginAdmin(String email, String password) throws BusinessException {
        if (email == null) {
            throw new BusinessException(400, "账号不能为空"); // 账号不能为空
        }
        if (password == null) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        if (password.isEmpty()) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        System.out.println(1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);

        User user = userMapper.selectOne(queryWrapper);   //查询数据库里值等于email并且没有注销的数据
        System.out.println(2);
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new BusinessException(400, "密码错误");
        }
        if (user.getRole() != 2) {
            throw new BusinessException(400, "非管理员");
        }
        System.out.println(3);
        return UserConverter.convertUser(user);
    }

    @Override
    public void updateUserRoleByUid(Integer uid, Integer role) throws BusinessException {
        User user = userMapper.selectById(uid);
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", uid).set("role", role);
        userMapper.update(user, updateWrapper);
    }

    @Override
    public void processClaim(Integer adminId, Integer cid, Integer status) throws BusinessException {
        if (adminId == null) {
            throw new BusinessException(400, "管理员编号不能为空");
        }
        if (cid == null) {
            throw new BusinessException(400, "认领编号不能为空");
        }
        if (status == null) {
            throw new BusinessException(400, "处理方式不能为空");
        }
        if (status != 0 && status != 1) {
            throw new BusinessException(400, "处理只能为1或0");
        }
        User user = userMapper.selectById(adminId);
        if (user == null) {
            throw new BusinessException(400, "管理员不存在");
        }
        // 修改申请状态
        User claim = userMapper.selectById(cid);
        if (claim == null) {
            throw new BusinessException(400, "认领不存在");
        }
        if (claim.getRole() != 3) {
            throw new BusinessException(400, "非认领申请");
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", cid).set("role", status);
        userMapper.update(claim, updateWrapper);
    }

    @Override
    public void processApplication(Integer adminId, Integer applicationId, Integer status) throws BusinessException {
        if (adminId == null) {
            throw new BusinessException(400, "管理员编号不能为空");
        }
        if (applicationId == null) {
            throw new BusinessException(400, "申请编号不能为空");
        }
        if (status == null) {
            throw new BusinessException(400, "处理态度不能为空");
        }
        if (status != 0 && status != 1) {
            throw new BusinessException(400, "处理态度只能为1或0");
        }
        User user = userMapper.selectById(adminId);
        if (user == null) {
            throw new BusinessException(400, "管理员不存在");
        }
        User application = userMapper.selectById(applicationId);
        if (application == null) {
            throw new BusinessException(400, "申请不存在");
        }
        if (application.getRole() != 1) {
            throw new BusinessException(400, "非用户申请");
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", applicationId).set("role", status);
        userMapper.update(application, updateWrapper);
    }
}
