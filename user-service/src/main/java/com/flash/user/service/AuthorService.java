package com.flash.user.service;

import com.flash.user.dao.Author;
import com.flash.user.utils.BusinessException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findByName(String name) throws BusinessException;

    Page<Author> findRandomly(Long num) throws BusinessException;

    Optional<Author> findById(String id) throws BusinessException;

    Long getNumOfAuthor() throws BusinessException;
}
