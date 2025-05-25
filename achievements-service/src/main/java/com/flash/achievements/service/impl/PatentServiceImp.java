package com.flash.achievements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.achievements.converter.PatentConverter;
import com.flash.achievements.converter.ProjectConverter;
import com.flash.achievements.dao.Patent;
import com.flash.achievements.dao.Project;
import com.flash.achievements.dao.User;
import com.flash.achievements.dto.PatentDTO;
import com.flash.achievements.mapper.PatentMapper;
import com.flash.achievements.mapper.UserMapper;
import com.flash.achievements.service.PatentService;
import com.flash.achievements.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 16:53
 */

@Slf4j
@Service
public class PatentServiceImp implements PatentService {
    @Autowired
    private PatentMapper patentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PatentDTO addPatent(PatentDTO patentDTO) throws BusinessException {
        if (patentDTO.getUserId() == null){
            throw new BusinessException(400, "申请人不存在");
        }
        if (patentDTO.getName() == null){
            throw new BusinessException(400, "专利名称为空");
        }
        if (patentDTO.getApplicant() == null){
            throw new BusinessException(400, "专利申请人为空");
        }
        if (patentDTO.getInventor() == null){
            throw new BusinessException(400, "专利发明人为空");
        }
        if (patentDTO.getType() == null){
            throw new BusinessException(400, "专利类型为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", patentDTO.getUserId());
        User user = userMapper.selectOne(queryWrapper); // 找到作者
        if (user == null){
            throw new BusinessException(400, "作者不存在");
        }
        QueryWrapper<Patent> patentQueryWrapper = new QueryWrapper<>();
        patentQueryWrapper.eq("user_id", patentDTO.getUserId());
        patentQueryWrapper.eq("name", patentDTO.getName());
        Patent patent= patentMapper.selectOne(patentQueryWrapper);
        if (patent != null){
            throw new BusinessException(400, "专利重复");
        }
        Patent newPatent = new Patent(null, patentDTO.getName(), patentDTO.getType(), patentDTO.getApplicationNumber(), patentDTO.getApplicant(), patentDTO.getInventor(), patentDTO.getAddress(), patentDTO.getApplyTime(), patentDTO.getUserId());
        patentMapper.insert(newPatent);
        return PatentConverter.convertPatent(newPatent);
    }

    @Override
    public void deletePatent(Integer id) throws BusinessException {
        if (id == null){
            throw new BusinessException(400, "专利不存在");
        }
        patentMapper.deleteById(id);
    }

    @Override
    public List<PatentDTO> getAllPatents() throws BusinessException {
        try {
            // 使用 MyBatis-Plus 提供的 selectList 方法查询所有记录
            List<Patent> patents = patentMapper.selectList(null);

            // 将 Award 实体列表转换为 AwardDTO 列表
            return patents.stream().map(PatentConverter::convertPatent).toList();
        } catch (Exception e) {
            // 捕获异常并抛出自定义业务异常
            throw new BusinessException(400, "查询项目失败");
        }
    }

    @Override
    public PatentDTO updatePatent(PatentDTO patentDTO) throws BusinessException {
        try {
            // 查找记录
            Patent patent = patentMapper.selectOne(new QueryWrapper<Patent>().eq("user_id", patentDTO.getUserId()).eq("name", patentDTO.getName()));
            if (patent == null) {
                throw new BusinessException(400, "未找到对应的项目");
            }
//            patent = PatentConverter.convertPatent(patentDTO);
            patent.setType(patentDTO.getType());
            patent.setAddress(patentDTO.getAddress());
            patent.setInventor(patentDTO.getInventor());
            patent.setApplicationNumber(patentDTO.getApplicationNumber());
            patent.setApplyTime(patentDTO.getApplyTime());
            patent.setName(patentDTO.getName());
            patent.setApplicant(patentDTO.getApplicant());
            patent.setUserId(patentDTO.getUserId());
            int rows = patentMapper.updateById(patent);
            if (rows == 0) {
                throw new BusinessException(400, "更新失败");
            }

            // 转换为 DTO 并返回
            return patentDTO;
        } catch (Exception e) {
            throw new BusinessException(400, "更新失败2");
        }
    }
}

