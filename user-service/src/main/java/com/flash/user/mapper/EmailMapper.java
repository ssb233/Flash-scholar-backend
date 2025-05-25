package com.flash.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash.user.dao.Email;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/7/11 下午7:50
 */
@Mapper
public interface EmailMapper extends BaseMapper<Email> {
}
