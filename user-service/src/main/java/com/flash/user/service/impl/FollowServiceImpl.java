package com.flash.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.user.converter.UserConverter;
import com.flash.user.dao.Follow;
import com.flash.user.dao.User;
import com.flash.user.dto.UserDTO;
import com.flash.user.mapper.FollowMapper;
import com.flash.user.mapper.UserMapper;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.FollowService;
import com.flash.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author Yury
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CustomResponse addOrCancelFollow(Integer idFrom, String idTo, String authorName, String authorInst) {
        CustomResponse customResponse = new CustomResponse();
        if (isFollowed(idFrom, idTo)) {
            QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id_from", idFrom).eq("id_to", idTo);
            followMapper.delete(queryWrapper);
            userService.updateStats(idFrom, "follows_count", false, 1);
            // userService.updateStats(idTo, "fans_count", false, 1);
            customResponse.setMessage("取消关注");
        } else {
            Follow follow = new Follow(null, idFrom, idTo, authorName, authorInst, new Date());
            followMapper.insert(follow);
            userService.updateStats(idFrom, "follows_count", true, 1);
            // userService.updateStats(idTo, "fans_count", true, 1);
            customResponse.setMessage("关注");
        }
        return customResponse;
    }

//    @Override
//    public List<UserDTO> getFans(Integer id) {
//        List<UserDTO> userDTOList = new ArrayList<>();
//        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id_to", id);
//        List<Follow> followList = followMapper.selectList(queryWrapper);
//        for (Follow follow : followList) {
//            User user = userMapper.selectById(follow.getUidFrom());
//            userDTOList.add(UserConverter.convertUser(user));
//        }
//        return userDTOList;
//    }

    @Override
    public Boolean isFollowed(Integer idFrom, String idTo) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_from", idFrom).eq("id_to", idTo);
        Follow follow = followMapper.selectOne(queryWrapper);
        return (follow != null);
    }

    @Override
    public List<Follow> getFollows(Integer id) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_from", id);

        return followMapper.selectList(queryWrapper);
    }

}
