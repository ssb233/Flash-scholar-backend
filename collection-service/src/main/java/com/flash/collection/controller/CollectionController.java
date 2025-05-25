package com.flash.collection.controller;

import com.flash.collection.dto.*;
import com.flash.collection.response.CustomResponse;
import com.flash.collection.service.CollectionService;
import com.flash.collection.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Tag(name = "收藏", description = "收藏信息")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @PostMapping("/favourite/add")
    @Operation(summary = "添加收藏夹", description = "添加收藏夹")
    public CustomResponse addFavourite(@RequestBody FavouriteAddDTO addDTO){
        CustomResponse response = new CustomResponse();
        Integer uid = addDTO.getUid();
        String fName = addDTO.getFName();
        String description = addDTO.getDescription();
        try {
            FavouritesDTO favouritesDTO = collectionService.addFavourite(uid, fName, description);
            response.setMessage("添加收藏夹成功");
            response.setData(favouritesDTO);
        } catch (BusinessException e){
            e.printStackTrace();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/favourite/delete")
    @Operation(summary = "删除收藏夹", description = "删除收藏夹")
    @Parameters(value = {
            @Parameter(name = "uid", description = "用户id", in = ParameterIn.QUERY, example = "1"),
            @Parameter(name = "fid", description = "收藏夹id", in = ParameterIn.QUERY, example = "1")
    })
    public CustomResponse deleteFavourite(@RequestParam Integer uid, @RequestParam Integer fid){
        CustomResponse response = new CustomResponse();
        try {
            collectionService.deleteFavourite(uid, fid);
            response.setMessage("删除成功");
        } catch (BusinessException e){
            e.printStackTrace();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }



    @PostMapping("/collections/add")
    @Operation(summary = "添加收藏", description = "添加收藏")
    public CustomResponse addCollection(@RequestBody AddDTO addDTO){
        CustomResponse response = new CustomResponse();
        Integer uid = addDTO.getUid();
        String achievementId = addDTO.getAchievementId();
        List<Integer> fid = addDTO.getFid();
        String title = addDTO.getTitle();
        String authors = addDTO.getAuthors();
        String journal = addDTO.getJournal();
        Integer quoteTime = addDTO.getQuoteTime();
        Date date = addDTO.getDate();
        try {
            List<AddResponse> collection = collectionService.addCollection(uid, achievementId, fid, title, authors, journal, quoteTime, date);
            response.setMessage("收藏成功");
            response.setData(collection);
        } catch (BusinessException e){
            e.printStackTrace();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @GetMapping("/collections/getCount/{achievementId}")
    @Operation(summary = "查询当前成果收藏数量", description = "查询当前成果是收藏数量")
    @Parameters(value = {
            @Parameter(name = "achievementId", description = "学术成果id", in = ParameterIn.PATH, example = "123")
    })
    public CustomResponse getCollectionCount(@PathVariable String achievementId){
        CustomResponse response = new CustomResponse();
        Integer count = collectionService.getCollectionCount(achievementId);
        response.setData(count);
        return response;
    }

    @GetMapping("/favourites/get/{uid}")
    @Operation(summary = "获取用户的所有收藏夹", description = "根据uid查找收藏夹")
    @Parameters(value = {
            @Parameter(name = "uid", description = "用户id", in = ParameterIn.PATH, example = "1")
    })
    public CustomResponse getFavouritesByUid(@PathVariable Integer uid){
        CustomResponse response = new CustomResponse();
        List<FavouritesDTO> favourites = collectionService.getFavouritesByUid(uid);
        response.setData(favourites);
        return response;
    }


    @GetMapping("/collections/get/{fid}")
    @Operation(summary = "查找收藏夹中的所有收藏信息", description = "根据fid查找所有收藏信息")
    @Parameters(value = {
            @Parameter(name = "uid", description = "用户id", in = ParameterIn.PATH, example = "1")
    })
    public CustomResponse getCollectionsByFid(@PathVariable Integer fid){
        CustomResponse response = new CustomResponse();
        List<CollectionDTO> collections = collectionService.getCollectionsByFid(fid);
        response.setData(collections);
        return response;
    }


    @DeleteMapping("/collections/delete")
    @Operation(summary = "删除收藏", description = "删除收藏")
    @Parameters(value = {
            @Parameter(name = "uid", description = "用户id", in = ParameterIn.QUERY, example = "1"),
            @Parameter(name = "achievementId", description = "学术成果id", in = ParameterIn.QUERY, example = "123"),
            @Parameter(name = "fid", description = "收藏夹id", in = ParameterIn.QUERY, example = "1")
    })
    public CustomResponse deleteCollection(@RequestParam Integer uid, @RequestParam String achievementId, @RequestParam Integer fid) throws BusinessException{
        CustomResponse response = new CustomResponse();
        try {
            collectionService.deleteCollection(uid, achievementId, fid);
            response.setMessage("删除成功");
        } catch (BusinessException e){
            e.printStackTrace();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
