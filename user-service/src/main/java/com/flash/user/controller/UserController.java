package com.flash.user.controller;

import com.flash.user.dto.RegisterDTO;
import com.flash.user.dto.UpdateUserInfoDTO;
import com.flash.user.dto.UserDTO;
import com.flash.user.dto.LoginDTO;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.UserService;
import com.flash.user.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author Yury
 */
@CrossOrigin
@RestController
@Tag(name = "用户", description = "用户信息")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/num")
    @Operation(summary = "注册用户个数", description = "state==0的用户")
    public CustomResponse getNumOfUser() {
        CustomResponse customResponse = new CustomResponse();
        Long num;
        try {
            num = userService.getNumOfUser();
            customResponse.setData(num);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }


    @GetMapping("/email/{email}")
    @Operation(summary = "由email获取用户信息", description = "由email获取用户信息")
    @Parameters(value = {
            @Parameter(name = "email", description = "用户邮箱", in = ParameterIn.PATH, example = "xxx@buaa.edu.cn")
    })
    public CustomResponse getUserByEmail(@PathVariable String email) {
        CustomResponse customResponse = new CustomResponse();
        UserDTO userDTO;
        try {
            userDTO = userService.getUserByEmail(email);
            customResponse.setData(userDTO);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 注册接口
     * @param registerDTO 包含 sid username password 的 map
     * @return CustomResponse对象
     */
    // 前端使用axios传递的data是Content-Type: application/json，需要用@RequestBody获取参数
    @PutMapping("/register")
    @Operation(summary = "用户注册，传email,nickname,password,activeCode")
    public CustomResponse userRegister(@RequestBody RegisterDTO registerDTO) {
        CustomResponse customResponse = new CustomResponse();
        String email = registerDTO.getEmail();
        String nickname = registerDTO.getNickname();
        String password = registerDTO.getPassword();
        String activeCode = registerDTO.getActiveCode();
        try {
            UserDTO userDTO = userService.register(email, nickname, password, activeCode);
            customResponse.setData(userDTO);
            customResponse.setMessage("注册成功");
        } catch (BusinessException e) {
            // e.printStackTrace();
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
            return customResponse;
        }
        return customResponse;
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public CustomResponse userLogin(@RequestBody LoginDTO loginDTO) {
        CustomResponse customResponse = new CustomResponse();
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        try {
            UserDTO userDTO = userService.userLogin(email, password);
            customResponse.setData(userDTO);
            customResponse.setMessage("登录成功");
        } catch (BusinessException e) {
            // e.printStackTrace();
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @GetMapping("/all")
    @Operation(summary = "获取所有用户信息")
    public CustomResponse getAllUsers() {
        CustomResponse customResponse = new CustomResponse();
        List<UserDTO> userDTOList = userService.getAllUsers();
        customResponse.setData(userDTOList);
        customResponse.setMessage("获取所有用户成功");
        return customResponse;
    }

    @GetMapping("/uid/{uid}")
    @Operation(summary = "获取一个用户信息", description = "由uid获取用户信息")
    @Parameters(value = {
            @Parameter(name = "uid", description = "用户id", in = ParameterIn.PATH, example = "1")
    })
    public CustomResponse getOneUserByUid(@PathVariable int uid) {
        CustomResponse customResponse = new CustomResponse();
        try {
            UserDTO userDTO = userService.getUserByUid(uid);
            customResponse.setData(userDTO);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/update/uid")
    @Operation(summary = "根据uid更改用户nickname")
    public CustomResponse updateUserInfo(@RequestBody UpdateUserInfoDTO updateUserInfoDTO) {
        CustomResponse customResponse = new CustomResponse();
        try {
            Integer uid = updateUserInfoDTO.getUid();
            String nickname = updateUserInfoDTO.getNickname();
            String fields = updateUserInfoDTO.getFields();
            userService.updateUserInfo(uid, nickname, fields);
            customResponse.setData("true");
            customResponse.setMessage("更改成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/password/uid/{uid}")
    @Operation(summary = "根据uid更改用户password")
    public CustomResponse updateUserPasswordBySid(@PathVariable Integer uid, String password, String confirmedPassword) {
        CustomResponse customResponse = new CustomResponse();
        try {
            userService.updateUserPasswordByUid(uid, password, confirmedPassword);
            customResponse.setData("true");
            customResponse.setMessage("更改成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @DeleteMapping("/uid/{uid}")
    @Operation(summary = "根据sid删除指定用户")
    @Parameters(value = {
            @Parameter(name = "uid", description = "用户id", in = ParameterIn.PATH, example = "233")
    })
    public CustomResponse deleteUserBySid(@PathVariable int uid) {
        CustomResponse customResponse = new CustomResponse();
        try {
            userService.deleteUserByUid(uid);
            customResponse.setData(true);
            customResponse.setMessage("删除成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }


    @GetMapping("/authorId/{authorId}")
    @Operation(summary = "由authorId获取uid", description = "")
    @Parameters(value = {
            @Parameter(name = "authorId", description = "作者Id", in = ParameterIn.PATH, example = "A5005593789")
    })
    public CustomResponse getUidByAuthorId(@PathVariable String authorId) {
        CustomResponse customResponse = new CustomResponse();
        Long uid;
        try {
            uid = userService.getUidByAuthorId(authorId);
            customResponse.setData(uid);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

