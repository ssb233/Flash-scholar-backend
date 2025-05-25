package com.flash.achievements.converter;

import com.flash.achievements.dao.Project;
import com.flash.achievements.dto.ProjectDTO;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 10:06
 */
public class ProjectConverter {
    public static ProjectDTO convertProject(Project project){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setMoney(project.getMoney());
        projectDTO.setHost(project.getHost());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setOrganizationId(project.getOrganizationId());
        projectDTO.setUserId(project.getUserId());
        return projectDTO;
    }
}

