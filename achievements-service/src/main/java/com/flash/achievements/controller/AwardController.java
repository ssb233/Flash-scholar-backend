package com.flash.achievements.controller;


import com.flash.achievements.dto.AwardDTO;
import com.flash.achievements.response.CustomResponse;
import com.flash.achievements.service.AwardService;
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
 * createDate   : 2024/12/1 11:20
 */

@CrossOrigin
@RestController
@Tag(name = "获奖", description = "获奖信息管理")
@RequestMapping("/api/award")


public class AwardController {

    @Autowired
    private AwardService awardService;

    /**
     * 添加获奖信息
     */
    @PostMapping("/add")
    @Operation(summary = "添加获奖信息", description = "提供获奖名称、获奖日期、获奖作者、关键字（可选）")
    public CustomResponse addAward(@RequestBody AwardDTO awardDTO){
        CustomResponse customResponse = new CustomResponse();
        Integer authorId = awardDTO.getAuthorId();
        String title = awardDTO.getTitle();
        String keyWords = awardDTO.getKeyWords();
        Date awardTime = awardDTO.getDate();
        try {
            AwardDTO addedAward = awardService.addAward(authorId, title, keyWords, awardTime);
            customResponse.setData(addedAward);
            customResponse.setMessage("添加成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 查询所有获奖信息
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有获奖信息", description = "返回所有获奖记录")
    public CustomResponse getAllAwards() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<AwardDTO> awards = awardService.getAllAwards();
            customResponse.setData(awards);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 根据作者ID查询获奖信息
     */
    @GetMapping("/author/{authorId}")
    @Operation(summary = "根据作者ID查询获奖信息", description = "返回指定作者ID的获奖记录")
    @Parameter(name = "authorId", description = "作者ID", in = ParameterIn.PATH, example = "1001")
    public CustomResponse getAwardsByAuthor(@PathVariable Integer authorId) {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<AwardDTO> awards = awardService.getAwardsByAuthor(authorId);
            customResponse.setData(awards);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 根据获奖标题查询获奖信息
     */
    @GetMapping("/title/{title}")
    @Operation(summary = "根据标题查询获奖信息", description = "返回指定标题的获奖记录")
    @Parameter(name = "title", description = "获奖标题", in = ParameterIn.PATH, example = "最佳论文奖")
    public CustomResponse getAwardByTitle(@PathVariable String title) {
        CustomResponse customResponse = new CustomResponse();
        try {
            AwardDTO award = awardService.getAwardByTitle(title);
            customResponse.setData(award);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 修改获奖信息
     */
    @PutMapping("/update")
    @Operation(summary = "修改获奖信息", description = "根据传入的获奖 ID 和新的获奖信息更新记录")
    public CustomResponse updateAward(@RequestBody AwardDTO awardDTO) {
        CustomResponse customResponse = new CustomResponse();
        Integer authorId = awardDTO.getAuthorId();
        String title = awardDTO.getTitle();
        String keyWords = awardDTO.getKeyWords();
        Date awardTime = awardDTO.getDate();
        try {
            AwardDTO updatedAward = awardService.updateAward(authorId, title, keyWords, awardTime);
            customResponse.setData(updatedAward);
            customResponse.setMessage("修改成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 删除获奖信息
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除获奖信息", description = "通过获奖 ID 删除指定的获奖信息")
    @Parameter(name = "id", description = "获奖 ID", in = ParameterIn.PATH, example = "1")
    public CustomResponse deleteAward(@PathVariable Integer id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            awardService.deleteAward(id);
            customResponse.setMessage("删除成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

