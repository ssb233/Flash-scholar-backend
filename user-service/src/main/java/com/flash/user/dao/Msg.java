package com.flash.user.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
    @TableId(type = IdType.AUTO)
    private Long id;         // 消息id
    private Long uid;     // 接收者sid
    private String title;
    private String content;     // 消息内容
    private String idSend;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date time;          // 发送消息的时间
    private Integer type;
}
