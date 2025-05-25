package com.flash.user.service;

import com.flash.user.dao.Follow;
import com.flash.user.dto.UserDTO;
import com.flash.user.response.CustomResponse;

import java.util.List;

public interface FollowService {
    CustomResponse addOrCancelFollow(Integer idFrom, String idTo, String authorName, String authorInst);

    // List<UserDTO> getFans(Integer id);
    Boolean isFollowed(Integer idFrom, String idTo);

    List<Follow> getFollows(Integer id);
}
