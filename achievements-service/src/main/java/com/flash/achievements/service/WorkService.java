package com.flash.achievements.service;

import com.flash.achievements.dao.Work;
import com.flash.achievements.dto.DocumentAdd;
import com.flash.achievements.dto.DocumentDTO;
import com.flash.achievements.utils.BusinessException;

import java.util.List;

public interface WorkService {
    Work addDocument(DocumentAdd documentAdd) throws BusinessException;
    void deleteDocument(Integer documentId) throws BusinessException;
    List<Work> getAllDocuments() throws BusinessException;
    DocumentDTO getDocumentById(String documentId) throws BusinessException;
    List<DocumentDTO> searchDocument(String keyWords) throws BusinessException;
    List<DocumentDTO> getReference(Integer documentId) throws BusinessException;
    List<DocumentDTO> getReferenced(Integer documentId) throws BusinessException;
}
