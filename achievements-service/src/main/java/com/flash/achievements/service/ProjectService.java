package com.flash.achievements.service;

import com.flash.achievements.dto.ProjectDTO;
import com.flash.achievements.utils.BusinessException;

import java.util.List;

public interface ProjectService {
    ProjectDTO addProject(Integer userId, Double money, Integer organizationId, String projectName) throws BusinessException;
    void deleteProject(Integer id) throws BusinessException;
    List<ProjectDTO> getAllProjects() throws BusinessException;
    ProjectDTO updateProject(Integer userId, Double money, Integer organizationId, String projectName) throws BusinessException;
}
