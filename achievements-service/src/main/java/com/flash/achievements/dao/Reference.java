package com.flash.achievements.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/9 16:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reference {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer mainId;
    private Integer referId;
}

