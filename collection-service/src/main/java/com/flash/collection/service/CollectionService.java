package com.flash.collection.service;

import com.flash.collection.dto.AddResponse;
import com.flash.collection.dto.CollectionDTO;
import com.flash.collection.dto.FavouritesDTO;
import com.flash.collection.utils.BusinessException;

import java.util.Date;
import java.util.List;

public interface CollectionService {
    FavouritesDTO addFavourite(Integer uid, String fName, String description) throws BusinessException;

    void deleteFavourite(Integer uid, Integer fid) throws BusinessException;

    Integer getCollectionCount(String achievementId);

    /**
     * 查找收藏夹中的收藏信息
     */
    List<CollectionDTO> getCollectionsByFid(Integer fid);

    /**
     * 查找用户的收藏夹
     */
    List<FavouritesDTO> getFavouritesByUid(Integer uid);

    FavouritesDTO getFavouriteByFid(Integer fid);

    /**
     * 添加收藏信息
     */
    List<AddResponse> addCollection(Integer uid, String achievementId, List<Integer> fid, String title, String authors, String journal, Integer quoteTime, Date date) throws BusinessException;

    /**
     * 删除收藏信息
     */
    void deleteCollection(Integer uid, String achievementId, Integer fid) throws BusinessException;

    /**
     * 查询当前成果是否被收藏
     */
//    boolean isCollected(Integer uid, Integer achievementId, String tag);
}
