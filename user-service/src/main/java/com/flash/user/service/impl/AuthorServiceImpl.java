package com.flash.user.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.RandomScoreFunction;
import com.flash.user.dao.Author;
import com.flash.user.repository.AuthorRepository;
import com.flash.user.service.AuthorService;
import com.flash.user.utils.BusinessException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author yury
 * @description: TODO
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Resource
    private AuthorRepository authorRepository;

    public Long getNumOfAuthor() {
        return authorRepository.count();
    }

    public Page<Author> findRandomly(Long num) {
        Pageable pageable = PageRequest.of(0, Math.toIntExact(num)); // 获取第 0 页，每页 10 个结果
        Page<Author> authorList = authorRepository.findRandomly(pageable);
//        for (int i = 0; i < num; i++) {
//            Optional<Author> author = authorRepository.findRandomly();
//            if (author.isPresent()) {
//                authorList.add(author.get());
//            }
//        }
        return authorList;
    }

    // 创建或更新产品
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // 根据 ID 查询产品
    public Optional<Author> findById(String id) throws BusinessException {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            throw new BusinessException(400, "作者不存在");
        }
        return authorRepository.findById(id);
    }

    // 根据名称查询产品
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    // 获取所有产品
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    // 删除产品
    public void deleteProduct(String id) {
        authorRepository.deleteById(id);
    }

}
