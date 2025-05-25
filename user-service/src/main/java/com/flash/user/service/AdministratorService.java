package com.flash.user.service;

import com.flash.user.dao.User;
import com.flash.user.dto.UserDTO;
import com.flash.user.utils.BusinessException;

public interface AdministratorService {
    UserDTO loginAdmin(String email, String password) throws BusinessException;
    void updateUserRoleByUid(Integer userId, Integer role) throws BusinessException;

    void processClaim(Integer adminId, Integer cid, Integer status) throws BusinessException;
    void processApplication(Integer adminId, Integer applicationId, Integer status) throws BusinessException;
}
