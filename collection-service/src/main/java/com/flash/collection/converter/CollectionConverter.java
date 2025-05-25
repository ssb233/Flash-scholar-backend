package com.flash.collection.converter;

import com.flash.collection.dao.Collection;
import com.flash.collection.dto.CollectionDTO;

public class CollectionConverter {

    public static CollectionDTO converterCollection(Collection collection){
        CollectionDTO collectionDTO = new CollectionDTO();
        collectionDTO.setAchievementId(collection.getAchievementId());
        collectionDTO.setTime(collection.getTime());
        collectionDTO.setTitle(collection.getTitle());
        collectionDTO.setAuthors(collection.getAuthors());
        collectionDTO.setJournal(collection.getJournal());
        collectionDTO.setQuoteTime(collection.getQuoteTime());
        collectionDTO.setDate(collection.getDate());
        collectionDTO.setFid(collection.getFid());

        return collectionDTO;
    }

    public static Collection converterCollection(CollectionDTO collectionDTO){
        Collection collection = new Collection();
        collection.setAchievementId(collectionDTO.getAchievementId());
        collection.setTime(collectionDTO.getTime());
        return collection;
    }

}
