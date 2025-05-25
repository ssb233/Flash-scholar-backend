package com.flash.achievements.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yury
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String email;
    private String nickname;
    private String name;
    private String password;
    private String avatar;
    private Integer role;   // 0 普通用户，1 科研人员，2 管理员
    private Integer state;   // 状态 0正常 1封禁 2注销
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date deleteDate;
    private Integer oid;
}
