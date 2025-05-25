package com.flash.collection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.collection.converter.CollectionConverter;
import com.flash.collection.dao.Collection;
import com.flash.collection.dao.Favourites;
import com.flash.collection.dto.AddResponse;
import com.flash.collection.dto.CollectionDTO;
import com.flash.collection.dto.FavouritesDTO;
import com.flash.collection.mapper.CollectionMapper;
import com.flash.collection.mapper.FavouritesMapper;
import com.flash.collection.service.CollectionService;
import com.flash.collection.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private FavouritesMapper favouritesMapper;

    //=========收藏夹相关方法=============
    @Override
    public FavouritesDTO addFavourite(Integer uid, String fName, String description) throws BusinessException {
        if(fName.equals("")){
            throw new BusinessException(400, "收藏夹名称不能为空");
        }
        QueryWrapper<Favourites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid)
                .eq("f_name", fName);
        Favourites favourite = favouritesMapper.selectOne(queryWrapper);
        if(favourite != null){
            throw new BusinessException(400, "收藏夹已存在");
        }

        Favourites favourites = new Favourites(
                null,
                fName,
                uid,
                new Date(),
                description
        );
        favouritesMapper.insert(favourites);
        return new FavouritesDTO(
                favourites.getFid(),
                favourites.getFName(),
                favourites.getUid(),
                favourites.getCreateTime(),
                favourites.getDescription()
        );
    }


    @Override
    public FavouritesDTO getFavouriteByFid(Integer fid){
        Favourites favourite = favouritesMapper.selectById(fid);
        if(favourite == null){
            return null;
        }
        return new FavouritesDTO(
                favourite.getFid(),
                favourite.getFName(),
                favourite.getUid(),
                favourite.getCreateTime(),
                favourite.getDescription()
        );
    }

    @Override
    public void deleteFavourite(Integer uid, Integer fid) throws BusinessException{
        QueryWrapper<Favourites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid)
                .eq("fid", fid);
        Favourites favourite = favouritesMapper.selectOne(queryWrapper);
        QueryWrapper<Collection> collectionQueryWrapper = new QueryWrapper<>();
        collectionQueryWrapper.eq("fid", fid)
                .eq("uid", uid);
        collectionMapper.delete(collectionQueryWrapper);

        if(favourite == null){
            throw new BusinessException(400, "收藏夹不存在");
        }
        if(favourite.getUid() != uid){
            throw new BusinessException(400, "无权限删除");
        }
        favouritesMapper.delete(queryWrapper);

    }

    @Override
    public Integer getCollectionCount(String achievementId) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("achievement_id", achievementId);
        return Math.toIntExact(collectionMapper.selectCount(queryWrapper));
    }

    @Override
    public List<CollectionDTO> getCollectionsByFid(Integer fid) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid", fid);
        List<Collection> collections = collectionMapper.selectList(queryWrapper);
        // 对查询到的每个收藏，添加对应的名称
        return collections.stream().map(
                collection -> {
                    CollectionDTO collectionDTO = CollectionConverter.converterCollection(collection);
                    return collectionDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<FavouritesDTO> getFavouritesByUid(Integer uid) {
        QueryWrapper<Favourites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        List<Favourites> favourites = favouritesMapper.selectList(queryWrapper);
        return favourites.stream().map(
                favourite -> {
                    FavouritesDTO favouritesDTO = new FavouritesDTO(
                            favourite.getFid(),
                            favourite.getFName(),
                            favourite.getUid(),
                            favourite.getCreateTime(),
                            favourite.getDescription()
                    );
                    return favouritesDTO;
                }
        ).collect(Collectors.toList());
    }


    @Override
    public List<AddResponse> addCollection(Integer uid, String achievementId, List<Integer> fid, String title, String authors, String journal, Integer quoteTime, Date date) throws BusinessException {
        checkEmpty(uid, achievementId);
        Date now = new Date();
        List<AddResponse> responseList = new ArrayList<>();
        for(Integer f : fid){
            Collection collection = collectionMapper.findByKey(uid, achievementId, f);
            Favourites favourites = favouritesMapper.selectById(f);
            if(collection != null){
                responseList.add(null);
                continue;
            }
            Collection newCollection = new Collection(
                    null,
                    uid,
                    achievementId,
                    now,
                    f,
                    quoteTime,
                    authors,
                    journal,
                    title,
                    date
            );
            collectionMapper.insert(newCollection);
            responseList.add(new AddResponse(favourites.getFName()));
        }

        return responseList;
    }

    @Override
    public void deleteCollection(Integer uid, String achievementId, Integer fid) throws BusinessException{
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid)
                .eq("achievement_id", achievementId)
                .eq("fid", fid);

        int rowsAffected = collectionMapper.delete(wrapper);
        if(rowsAffected == 0){
            throw new BusinessException(400, "该收藏不存在");
        }
    }

    private void checkEmpty(Integer uid, String achievementId) throws BusinessException{
        if(uid == null || achievementId == null){
            throw new BusinessException(400, "数据不能为空");
        }
    }
//    @Override
//    public boolean isCollected(Integer uid, Integer achievementId, Integer achievementType, Integer fid) {
//        Collection collection = collectionMapper.findByKey(uid, achievementId, achievementType, fid);
//        if(collection == null){
//            return false;
//        } else {
//            return true;
//        }
//    }
}
