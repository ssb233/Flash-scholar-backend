package com.flash.achievements.converter;


import com.flash.achievements.dao.Award;
import com.flash.achievements.dto.AwardDTO;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/1 15:42
 */
public class AwardConverter {
    public static AwardDTO convertAward(Award award, String authorName){
        AwardDTO awardDTO = new AwardDTO();
        awardDTO.setId(award.getId());
        awardDTO.setAuthorId(award.getAuthorId());
        awardDTO.setAuthorName(authorName);
        awardDTO.setTitle(award.getTitle());
        awardDTO.setKeyWords(award.getKeyWords());
        awardDTO.setDate(award.getAwardTime());
        return awardDTO;
    }

    public static Award convertAward(AwardDTO awardDTO){
        Award award = new Award();
        award.setId(awardDTO.getId());
        award.setAuthorId(awardDTO.getAuthorId());
        award.setTitle(awardDTO.getTitle());
        award.setKeyWords(awardDTO.getKeyWords());
        award.setAwardTime(awardDTO.getDate());
        return award;
    }
}

