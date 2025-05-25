package com.flash.achievements.controller;

import com.flash.achievements.dto.PatentDTO;
import com.flash.achievements.response.CustomResponse;
import com.flash.achievements.service.PatentService;
import com.flash.achievements.service.PatentService;
import com.flash.achievements.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 16:50
 */

@CrossOrigin
@RestController
@Tag(name = "专利", description = "专利信息管理")
@RequestMapping("/api/patent")
public class PatentController {
    @Autowired
    private PatentService patentService;

    /**
     * 添加专利信息
     */
    @PostMapping("/add")
    @Operation(summary = "添加专利信息", description = "提供专利相关信息）")
    public CustomResponse addPatent(@RequestBody PatentDTO patentDTO){
        CustomResponse customResponse = new CustomResponse();
        try {
            PatentDTO addedPatent= patentService.addPatent(patentDTO);
            customResponse.setData(addedPatent);
            customResponse.setMessage("添加成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 查询所有专利信息
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有专利", description = "返回所有的专利")
    public CustomResponse getAllPatents() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<PatentDTO> patents = patentService.getAllPatents();
            customResponse.setData(patents);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 修改专利信息
     */
    @PutMapping("/update")
    @Operation(summary = "修改专利信息", description = "根据传入的专利 ID 和新的专利信息更新记录")
    public CustomResponse updatePatent(@RequestBody PatentDTO patentDTO) {
        CustomResponse customResponse = new CustomResponse();
        try {
            PatentDTO updatedPatent = patentService.updatePatent(patentDTO);
            customResponse.setData(updatedPatent);
            customResponse.setMessage("修改成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 删除专利信息
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除专利信息", description = "通过专利 ID 删除指定的专利信息")
    @Parameter(name = "id", description = "专利 ID", in = ParameterIn.PATH, example = "1")
    public CustomResponse deletePatent(@PathVariable Integer id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            patentService.deletePatent(id);
            customResponse.setMessage("删除成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

