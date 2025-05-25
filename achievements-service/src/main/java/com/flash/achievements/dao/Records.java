package com.flash.achievements.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/8 20:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Records {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String authorId;
    private Integer have_id;
    private Integer have_type;
}

