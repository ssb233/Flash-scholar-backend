package com.flash.achievements.converter;

import com.flash.achievements.dao.Work;
import com.flash.achievements.dto.*;

import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 11:24
 */
public class WorkConverter {
    private static int rank = 0;
    public static DocumentDTO documentConverter(Work work, List<AuthorInfo> authorInfos, List<SchoolInfo> schoolInfos, List<SourceDTO> sourceDTOS, List<ConceptDTO> conceptDTOS){
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocumentId(work.getId());
        documentDTO.setName(work.getTitle());
        documentDTO.setAbstractPart(work.getAbstractPart());
        documentDTO.setConceptDTOS(conceptDTOS);
        documentDTO.setSourceDTOS(sourceDTOS);
        documentDTO.setCitationCount(work.getCitationCount());
        documentDTO.setDoi(work.getDoi());
        documentDTO.setPublishTime(work.getPublishTime());
        documentDTO.setCollectionNum(work.getCollectionNum());
        documentDTO.setType(work.getType());
        documentDTO.setAuthors(authorInfos);
        documentDTO.setSchools(schoolInfos);
        documentDTO.setRank(rank++);
        return documentDTO;
    }
}

