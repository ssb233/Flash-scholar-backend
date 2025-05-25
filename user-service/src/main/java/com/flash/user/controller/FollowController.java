package com.flash.user.controller;

import com.flash.user.dao.Follow;
import com.flash.user.dto.UserDTO;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author Yury
 */
@CrossOrigin
@RestController
@Tag(name = "关注api")
@RequestMapping("/api/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/addOrCancel")
    @Operation(summary = "添加或取消关注")
    public CustomResponse addOrCancelFollow(
            @RequestParam("id_from") Integer idFrom,
            @RequestParam("id_to") String idTo,
            @RequestParam("author_name") String authorName,
            @RequestParam("author_inst") String authorInst) {

        return followService.addOrCancelFollow(idFrom, idTo, authorName, authorInst);
    }

    @GetMapping("/is-followed")
    @Operation(summary = "是否关注")
    public CustomResponse isFollowed(@RequestParam("id_from") Integer idFrom,
                                     @RequestParam("id_to") String idTo) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setData(followService.isFollowed(idFrom, idTo));
        return customResponse;
    }

//    @GetMapping("/fans")
//    @Operation(summary = "获得用户id粉丝列表")
//    public CustomResponse getFans(@RequestParam("id") Integer id) {
//        CustomResponse customResponse = new CustomResponse();
//        List<UserDTO> userDTOList = followService.getFans(id);
//
//        customResponse.setData(userDTOList);
//        return customResponse;
//    }

    @GetMapping("/followers")
    @Operation(summary = "获得id关注列表")
    public CustomResponse getFollows(@RequestParam("id") Integer id) {
        CustomResponse customResponse = new CustomResponse();
        List<Follow> followList = followService.getFollows(id);

        customResponse.setData(followList);
        return customResponse;
    }

    // 微服务接口
//    @GetMapping("/follow-service/follows")
//    @Operation(summary = "服务接口：获得uid关注列表")
//    public List<UserDTO> getFollowsService(@RequestParam("uid") Integer uid) {
//        return followService.getFollows(uid);
//    }
//
//    @GetMapping("/follow-service/fans")
//    @Operation(summary = "服务接口：获得uid粉丝列表")
//    public List<UserDTO> getFansService(@RequestParam("uid") Integer uid) {
//        return followService.getFans(uid);
//    }
}
