package com.flash.achievements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.achievements.converter.AwardConverter;
import com.flash.achievements.converter.ProjectConverter;
import com.flash.achievements.dao.Award;
import com.flash.achievements.dao.Project;
import com.flash.achievements.dao.User;
import com.flash.achievements.dto.ProjectDTO;
import com.flash.achievements.mapper.AwardMapper;
import com.flash.achievements.mapper.ProjectMapper;
import com.flash.achievements.mapper.UserMapper;
import com.flash.achievements.service.ProjectService;
import com.flash.achievements.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 00:23
 */

@Slf4j
@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ProjectDTO addProject(Integer userId, Double money, Integer organizationId, String projectName) throws BusinessException {
        if (userId == null){
            throw new BusinessException(400, "项目负责人不存在");
        }
        if (organizationId == null){
            throw new BusinessException(400, "所在机构不存在");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", userId);
        User user = userMapper.selectOne(queryWrapper); // 找到作者
        if (user == null){
            throw new BusinessException(400, "作者不存在");
        }
        String host = user.getName();
        QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
        projectQueryWrapper.eq("user_id", userId);
        projectQueryWrapper.eq("project_name", projectName);
        Project project = projectMapper.selectOne(projectQueryWrapper);
        if (project != null){
            throw new BusinessException(400, "项目重复");
        }
        Project newProject = new Project(null, projectName, host, organizationId, money, userId);
        projectMapper.insert(newProject);
        return ProjectConverter.convertProject(newProject);
    }

    @Override
    public void deleteProject(Integer id) throws BusinessException {
        if (id == null){
            throw new BusinessException(400, "项目不存在");
        }
        projectMapper.deleteById(id);
    }

    @Override
    public List<ProjectDTO> getAllProjects() throws BusinessException {
        try {
            // 使用 MyBatis-Plus 提供的 selectList 方法查询所有记录
            List<Project> projects = projectMapper.selectList(null);

            // 将 Award 实体列表转换为 AwardDTO 列表
            return projects.stream().map(ProjectConverter::convertProject).toList();
        } catch (Exception e) {
            // 捕获异常并抛出自定义业务异常
            throw new BusinessException(400, "查询项目失败");
        }
    }

    @Override
    public ProjectDTO updateProject(Integer userId, Double money, Integer organizationId, String projectName) throws BusinessException {
        try {
            // 查找记录
            Project project = projectMapper.selectOne(new QueryWrapper<Project>().eq("user_id", userId).eq("project_name", projectName));
            if (project == null) {
                throw new BusinessException(400, "未找到对应的项目");
            }
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uid", userId);
            User user = userMapper.selectOne(queryWrapper); // 找到作者
            // 更新记录
            project.setHost(user.getName());
            project.setOrganizationId(organizationId);
            project.setMoney(money);
            int rows = projectMapper.updateById(project);
            if (rows == 0) {
                throw new BusinessException(400, "更新失败");
            }

            // 转换为 DTO 并返回
            return ProjectConverter.convertProject(project);
        } catch (Exception e) {
            throw new BusinessException(400, "更新失败");
        }
    }
}

