package com.flash.achievements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.flash.achievements.converter.AwardConverter;
import com.flash.achievements.dao.Award;
import com.flash.achievements.dao.User;
import com.flash.achievements.dto.AwardDTO;
import com.flash.achievements.mapper.AwardMapper;
import com.flash.achievements.mapper.UserMapper;
import com.flash.achievements.service.AwardService;
import com.flash.achievements.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/1 11:34
 */
@Slf4j
@Service
public class AwardServiceImp implements AwardService {
    @Autowired
    private AwardMapper awardMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public AwardDTO addAward(Integer authorId, String title, String keyWords, Date awardDate) throws BusinessException {
        if (authorId == null){
            throw new BusinessException(400, "作者不存在");
        }
        if (title == null){
            throw new BusinessException(400, "奖项名不能为空");
        }
        if (keyWords == null){
            keyWords = "";
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", authorId);
        User user = userMapper.selectOne(queryWrapper); // 找到作者
        if (user == null){
            throw new BusinessException(400, "作者不存在");
        }
        QueryWrapper<Award> awardQueryWrapper = new QueryWrapper<>();
        awardQueryWrapper.eq("author_id", authorId);
        awardQueryWrapper.eq("title", title);
        Award award = awardMapper.selectOne(awardQueryWrapper);
        if (award != null){
            throw new BusinessException(400, "奖项重复");
        }
        Award newAward = new Award(null, authorId, title, keyWords, awardDate);
        awardMapper.insert(newAward);

        return AwardConverter.convertAward(newAward, user.getName());
    }

    @Override
    public void deleteAward(Integer id) throws BusinessException {
        if (id == null){
            throw new BusinessException(400, "奖项不存在");
        }
        awardMapper.deleteById(id);
    }

    @Override
    public List<AwardDTO> getAllAwards() throws BusinessException {
        try {
            // 使用 MyBatis-Plus 提供的 selectList 方法查询所有记录
            List<Award> awards = awardMapper.selectList(null);

            // 将 Award 实体列表转换为 AwardDTO 列表
            return awards.stream().map(award -> {
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("uid", award.getAuthorId());
                User user = userMapper.selectOne(queryWrapper); // 找到作者
                return AwardConverter.convertAward(award, user.getName());
            }).toList();
        } catch (Exception e) {
            // 捕获异常并抛出自定义业务异常
            throw new BusinessException(400, "查询获奖记录失败");
        }
    }

    @Override
    public List<AwardDTO> getAwardsByAuthor(Integer authorId) throws BusinessException {
        try {
            List<Award> awards = awardMapper.selectList(new QueryWrapper<Award>().eq("author_id", authorId));

            // 转换为 DTO 列表
            return awards.stream().map(award -> {
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("uid", award.getAuthorId());
                User user = userMapper.selectOne(queryWrapper); // 找到作者
                return AwardConverter.convertAward(award, user.getName());
            }).toList();
        } catch (Exception e) {
            throw new BusinessException(400, "查询作者的获奖记录失败");
        }
    }

    @Override
    public AwardDTO getAwardByTitle(String title) throws BusinessException {
        try {
            // 查询单条记录
            Award award = awardMapper.selectOne(new QueryWrapper<Award>().eq("title", title));

            if (award == null) {
                throw new BusinessException(400, "未找到对应的获奖记录");
            }

            // 转换为 DTO 并返回
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uid", award.getAuthorId());
            User user = userMapper.selectOne(queryWrapper); // 找到作者
            return AwardConverter.convertAward(award, user.getName());
        } catch (Exception e) {
            throw new BusinessException(400, "查询获奖记录失败");
        }
    }

    @Override
    public AwardDTO updateAward(Integer authorId, String title, String keyWords, Date awardDate) throws BusinessException {
        try {
            // 查找记录
            Award award = awardMapper.selectOne(new QueryWrapper<Award>().eq("author_id", authorId).eq("title", title));
            if (award == null) {
                throw new BusinessException(400, "未找到对应的获奖记录");
            }

            // 更新记录
            award.setKeyWords(keyWords);
            award.setAwardTime(awardDate);
            int rows = awardMapper.updateById(award);
            if (rows == 0) {
                throw new BusinessException(400, "更新失败");
            }

            // 转换为 DTO 并返回
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uid", award.getAuthorId());
            User user = userMapper.selectOne(queryWrapper); // 找到作者
            return AwardConverter.convertAward(award, user.getName());
        } catch (Exception e) {
            throw new BusinessException(400, "更新获奖记录失败");
        }
    }

}

