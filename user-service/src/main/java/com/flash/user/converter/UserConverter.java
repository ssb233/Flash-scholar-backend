package com.flash.user.converter;


import com.flash.user.dao.User;
import com.flash.user.dto.UserDTO;

/**
 * Description:
 *
 * @author Yury
 */
public class UserConverter {

    public static UserDTO convertUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(user.getUid());
        userDTO.setAuthorId(user.getAuthorId());
        userDTO.setEmail(user.getEmail());
        userDTO.setNickname(user.getNickname());
        //userDTO.setPassword(user.getPassword());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setFields(user.getFields());
        userDTO.setFollowsCount(user.getFollowsCount());
        userDTO.setFansCount(user.getFansCount());
        userDTO.setRole(user.getRole());
        userDTO.setCreateDate(user.getCreateDate());

        // userDTO.setDeleteDate(user.getDeleteDate());
        return userDTO;
    }

    public static User convertUser(UserDTO userDTO) {
        User user = new User();
        // user.setPassword(userDTO.getPassword());
        user.setAvatar(userDTO.getAvatar());
        return user;
    }
}
