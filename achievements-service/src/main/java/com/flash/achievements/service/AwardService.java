package com.flash.achievements.service;


import com.flash.achievements.dto.AwardDTO;
import com.flash.achievements.utils.BusinessException;

import java.util.Date;
import java.util.List;

public interface AwardService {
    AwardDTO addAward(Integer authorId, String title, String keyWords, Date awardDate) throws BusinessException;
    void deleteAward(Integer id) throws BusinessException;
    List<AwardDTO> getAllAwards() throws BusinessException;
    List<AwardDTO> getAwardsByAuthor(Integer authorId) throws BusinessException;
    AwardDTO getAwardByTitle(String title) throws BusinessException;
    AwardDTO updateAward(Integer authorId, String title, String keyWords, Date awardDate) throws BusinessException;
}
