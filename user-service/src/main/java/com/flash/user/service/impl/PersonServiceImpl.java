package com.flash.user.service.impl;


import com.flash.user.dao.Coauthor;
import com.flash.user.dao.Person;
import com.flash.user.dto.PersonDTO;
import com.flash.user.dto.PersonNetDTO;
import com.flash.user.repository.PersonRepository;
import com.flash.user.service.PersonService;
import com.flash.user.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @className:
 * @author: zhaopeng
 * @description: TODO(......)
 * @date: 2021年12月27日 9:49
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void addPerson(PersonDTO personDTO) throws BusinessException {
        String authorId1 = personDTO.getAuthorId1();
        String authorId2 = personDTO.getAuthorId2();
        String name1 = personDTO.getName1();
        String name2 = personDTO.getName2();
        Person person1 =  personRepository.findPersonByAuthorId(authorId1);
        Person person2 =  personRepository.findPersonByAuthorId(authorId2);
        if (person1 == null) {
            person1 = new Person(authorId1, name1);
        }
        if (person2 == null) {
            person2 = new Person(authorId2, name2);
        }
        int flag = 0;
        // if (person1.getCoauthors().contains(person2)) {}
        for (Coauthor coauthor : person1.getCoauthors()) {
            if (Objects.equals(coauthor.person.getAuthorId(), authorId2)) {
                coauthor.count++;
                flag = 1;
                for (Coauthor coauthor2 : person2.getCoauthors()) {
                    if (Objects.equals(coauthor2.person.getAuthorId(), authorId1)) {
                        coauthor2.count++;
                        break;
                    }
                }
                break;
            }
        }
        if (flag == 0) {
            person1.getCoauthors().add(new Coauthor(person2, 1));
            person2.getCoauthors().add(new Coauthor(person1, 1));
        }

        //
        personRepository.save(person1);
        personRepository.save(person2);
    }

    public List<PersonNetDTO> getCoauthors(String authorId) throws BusinessException {
        Person person = personRepository.getPersonByAuthorId(authorId);
        if (person == null) {
            throw new BusinessException(400, "作者不存在");
        }
        // System.out.println(person);
        Set<Coauthor> coauthors = person.getCoauthors();
        List<PersonNetDTO> personNetDTOS = new ArrayList<>();
        personNetDTOS.add(
                new PersonNetDTO(
                        person.getAuthorId(),
                        person.getName(),
                        0,
                        0,
                        0,
                        0,
                        0
                )
        );
        for (Coauthor coauthor : coauthors) {
            personNetDTOS.add(
                    new PersonNetDTO(
                            coauthor.person.getAuthorId(),
                            coauthor.person.getName(),
                            0,
                            0,
                            0,
                            coauthor.count,
                            1
                    )
            );
        }
        return personNetDTOS;
    }

    public void gettest() {
//        // 创建节点
//        MovieEntity movie = new MovieEntity("你的名字", "影片讲述了男女高中生在梦中相遇，并寻找彼此的故事。");
//        Roles roles1 = new Roles(new Person(1998, "上白石萌音"), Collections.singletonList("宫水三叶"));
//        Roles roles2 = new Roles(new Person(1993, "神木隆之介"), Collections.singletonList("立花泷"));
//        Person director = new Person(1973, "新海诚");
//// 添加关系
//        movie.getActorsAndRoles().add(roles1);
//        movie.getActorsAndRoles().add(roles2);
//        movie.getDirectors().add(director);
//// 存入图数据库持久化
//        movieRepository.save(movie);

    }


}
