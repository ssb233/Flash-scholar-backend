package com.flash.user.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description:
 *
 * @author Yury
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer idFrom;
    private String idTo;
    private String authorName;
    private String authorInst;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date createTime;
}
