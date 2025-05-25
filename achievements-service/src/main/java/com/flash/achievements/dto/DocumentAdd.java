package com.flash.achievements.dto;

import com.flash.achievements.dao.Source;
import com.flash.achievements.dao.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/11 15:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentAdd {
    private String workId;
    private List<AuthorDTO> authorDTOS;
    private List<InstitutionDTO> institutionDTOS;
    private String title;
    private String abstracts;
    private List<ConceptDTO> conceptDTOS;
    private Integer cited;
    private String type;
    private String doi;
    private Date publishTime;
    private List<SourceDTO> sources;
    private List<RelatedDTO> relatedDTOS;
    private List<ReferencesDTO> referencesDTOS;
}

