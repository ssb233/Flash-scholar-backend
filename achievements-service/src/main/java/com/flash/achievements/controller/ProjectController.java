package com.flash.achievements.controller;

import com.flash.achievements.dao.Project;
import com.flash.achievements.dto.AwardDTO;
import com.flash.achievements.dto.ProjectDTO;
import com.flash.achievements.response.CustomResponse;
import com.flash.achievements.service.ProjectService;
import com.flash.achievements.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/1 23:22
 */

@CrossOrigin
@RestController
@Tag(name = "项目", description = "项目信息管理")
@RequestMapping("/api/project")

public class ProjectController {
    @Autowired
    private ProjectService projectService;

    /**
     * 添加项目信息
     */
    @PostMapping("/add")
    @Operation(summary = "添加项目信息", description = "提供项目名称、项目所属机构id、项目负责人id、经费（可选）")
    public CustomResponse addProject(@RequestBody ProjectDTO projectDTO){
        CustomResponse customResponse = new CustomResponse();
        Integer userId = projectDTO.getUserId();
        Double money = projectDTO.getMoney();
        String projectName = projectDTO.getProjectName();
        Integer organizationId = projectDTO.getOrganizationId();
        try {
            ProjectDTO addedProject= projectService.addProject(userId, money, organizationId, projectName);
            customResponse.setData(addedProject);
            customResponse.setMessage("添加成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 查询所有项目信息
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有项目", description = "返回所有的项目")
    public CustomResponse getAllProjects() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<ProjectDTO> projects = projectService.getAllProjects();
            customResponse.setData(projects);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 修改项目信息
     */
    @PutMapping("/update")
    @Operation(summary = "修改项目信息", description = "根据传入的项目 ID 和新的项目信息更新记录")
    public CustomResponse updateProject(@RequestBody ProjectDTO projectDTO) {
        CustomResponse customResponse = new CustomResponse();
        Integer userId = projectDTO.getUserId();
        Double money = projectDTO.getMoney();
        String projectName = projectDTO.getProjectName();
        Integer organizationId = projectDTO.getOrganizationId();
        try {
            ProjectDTO updatedProject = projectService.updateProject(userId, money, organizationId, projectName);
            customResponse.setData(updatedProject);
            customResponse.setMessage("修改成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 删除项目信息
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除项目信息", description = "通过项目 ID 删除指定的项目信息")
    @Parameter(name = "id", description = "项目 ID", in = ParameterIn.PATH, example = "1")
    public CustomResponse deleteProject(@PathVariable Integer id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            projectService.deleteProject(id);
            customResponse.setMessage("删除成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

