package com.flash.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.flash.user.converter.UserConverter;
import com.flash.user.dao.Email;
import com.flash.user.dao.User;
import com.flash.user.dto.UpdateUserInfoDTO;
import com.flash.user.dto.UserDTO;
import com.flash.user.mapper.EmailMapper;
import com.flash.user.mapper.UserMapper;
import com.flash.user.service.UserService;
import com.flash.user.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * Description:
 *
 * @author Yury
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public Long getNumOfUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        return userMapper.selectCount(queryWrapper);
    }


    @Override
    public UserDTO getUserByUid(Integer uid) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }

        return UserConverter.convertUser(user);
    }

    /**
     * 用户注册
     */
    @Override
    public UserDTO register(String email, String nickname, String password, String activeCode) throws BusinessException {
        if (email == null) {
            throw new BusinessException(400, "账号不能为空"); // 账号不能为空
        }
        if (nickname == null) {
            throw new BusinessException(400, "用户名不能为空"); // 用户名不能为空
        }
        nickname = nickname.trim();   //删掉用户名的空白符
        if (nickname.isEmpty()) {
            throw new BusinessException(400, "用户名不能为空"); // 用户名不能为空
        }
        if (password == null) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        if (password.isEmpty()) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        if (nickname.length() > 20) {
            throw new BusinessException(400, "账号长度不能大于50"); // 账号长度不能大于50
        }
        if (password.length() > 50) {
            throw new BusinessException(400, "密码长度不能大于50"); // 密码长度不能大于50

        }
//        if (!password.equals(confirmedPassword)) {
//            customResponse.setCode(403);
//            customResponse.setMessage("两次输入的密码不一致");
//            return customResponse;
//        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userMapper.selectOne(queryWrapper);   //查询数据库里值等于email并且没有注销的数据
        if (user != null) {
            throw new BusinessException(400, "邮箱已注册"); // 邮箱已注册
        }

        QueryWrapper<Email> emailQueryWrapper = new QueryWrapper<>();
        emailQueryWrapper.eq("email", email);
        Email emailUsed = emailMapper.selectOne(emailQueryWrapper);
        if (!Objects.equals(emailUsed.getActiveCode(), activeCode)) {
            throw new BusinessException(400, "验证码错误"); // 验证码错误
        }

        String avatarUrl = "cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png";
        Date now = new Date();
        User new_user = new User(
                null,
                null,
                email,
                nickname,
                password,
                avatarUrl,
                "还没有领域哦~",
                0,
                0,
                0,
                0,
                now,
                null
        );
        System.out.println(new_user);
        userMapper.insert(new_user);

        return UserConverter.convertUser(new_user);
    }

    @Override
    public UserDTO userLogin(String email, String password) throws BusinessException {
        if (email == null) {
            throw new BusinessException(400, "账号不能为空"); // 账号不能为空
        }
        if (password == null) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        if (password.isEmpty()) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        System.out.println(email);
        System.out.println(password);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userMapper.selectOne(queryWrapper);   //查询数据库里值等于email并且没有注销的数据
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new BusinessException(400, "密码错误");
        }
        return UserConverter.convertUser(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }
        return UserConverter.convertUser(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<UserDTO> userDTOList = new ArrayList<>();
        userMapper.selectList(queryWrapper).forEach(u -> userDTOList.add(UserConverter.convertUser(u)));
        return userDTOList;
    }

    @Override
    public void updateUserInfo(Integer uid, String nickname, String fields) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", uid);
        if (nickname != null) {
            // TODO：判断合法性
            if (nickname.trim().isEmpty()) {
                throw new BusinessException(400, "昵称不能为空");
            }
            if (nickname.length() > 24) {
                throw new BusinessException(400, "输入字符过长");
            }
            updateWrapper.set("nickname", nickname);
        }
        if (fields == null || fields.isEmpty()) {
            fields = "还没有领域哦~";
        }

        updateWrapper.set("fields", fields);

        userMapper.update(null, updateWrapper);
    }

    @Override
    public void updateUserPasswordByUid(Integer uid, String password, String confirmedPassword) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        queryWrapper.eq("state", 0);
        User user = userMapper.selectOne(queryWrapper);   //查询数据库里值等于username并且没有注销的数据
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }

        if (password == null || password.isEmpty()) {
            throw new BusinessException(400, "密码不能为空");
        }
        if (password.length() > 50) {
            throw new BusinessException(400, "密码长度不能大于50");
        }
        if (!password.equals(confirmedPassword)) {
            throw new BusinessException(400, "两次输入的密码不一致");
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", uid).set("password", password);
        userMapper.update(null, updateWrapper);
    }

    @Override
    public void deleteUserByUid(Integer uid) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        int res = userMapper.delete(queryWrapper);
        if (res == 0) {
            throw new BusinessException(400, "用户不存在");
        }
    }

    @Override
    public void updateStats(Integer uid, String column, boolean increase, Integer count) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", uid);
        if (increase) {
            updateWrapper.setSql(column + " = " + column + " + " + count);
        } else {
            // 更新后的字段不能小于0
            updateWrapper.setSql(column + " = CASE WHEN " + column + " - " + count + " < 0 THEN 0 ELSE " + column + " - " + count + " END");
        }
        userMapper.update(null, updateWrapper);
    }

    @Override
    public Long getUidByAuthorId(String authorId) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id", authorId);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return (long) -1;
            // throw new BusinessException(400, "没有该学者对应的用户");
        }
        return user.getUid();
    }
}
