package com.flash.user.service;

import com.flash.user.dao.Coauthor;
import com.flash.user.dao.Person;
import com.flash.user.dto.PersonDTO;
import com.flash.user.dto.PersonNetDTO;
import com.flash.user.utils.BusinessException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonService {
    public void gettest();

    void addPerson(PersonDTO personDTO) throws BusinessException;

    List<PersonNetDTO> getCoauthors(String authorId) throws BusinessException;
}
