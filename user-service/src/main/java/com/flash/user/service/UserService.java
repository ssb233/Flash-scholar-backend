package com.flash.user.service;


import com.flash.user.dto.UpdateUserInfoDTO;
import com.flash.user.dto.UserDTO;
import com.flash.user.utils.BusinessException;

import java.util.List;

public interface UserService {
    Long getNumOfUser() throws BusinessException;

    UserDTO getUserByUid(Integer uid) throws BusinessException;

    UserDTO register(String email, String name, String password, String activeCode) throws BusinessException;

    UserDTO userLogin(String email, String password) throws BusinessException;

    UserDTO getUserByEmail(String email) throws BusinessException;

    List<UserDTO> getAllUsers();

    void updateUserInfo(Integer uid, String email, String nickname) throws BusinessException;

    void updateUserPasswordByUid(Integer uid, String password, String confirmedPassword) throws BusinessException;

    void deleteUserByUid(Integer uid) throws BusinessException;

    void updateStats(Integer uid, String column, boolean increase, Integer count);

    Long getUidByAuthorId(String authorId) throws BusinessException;
//    /**
//     *  用户注册
//     * @param user
//     */
//    void add(User user);
//
//    /**
//     *  根据激活码查找用户
//     * @param activeCode
//     * @return
//     */
//    User getUserByActiveCode(String activeCode);
//
//    /**
//     * 修改
//     * @param user
//     */
//    void modify(User user);
//
//    /**
//     * 登录
//     * @param user
//     * @return
//     */
//    User get(User user);

}
