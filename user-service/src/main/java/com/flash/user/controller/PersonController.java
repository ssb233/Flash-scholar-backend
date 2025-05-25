package com.flash.user.controller;

import com.flash.user.dao.Person;
import com.flash.user.dto.PersonDTO;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.PersonService;
import com.flash.user.utils.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className:
 * @author: zhaopeng
 * @description: TODO(......)
 */
@RestController
@RequestMapping("/api/person")
@Api(tags="neo4j人物关系网")
public class PersonController {

    @Autowired
    private PersonService personService;


    @PostMapping("/add")
    @ApiOperation(value="添加对象节点")
    public CustomResponse addPerson(PersonDTO personDTO) {
        CustomResponse customResponse = new CustomResponse();
        try {
            personService.addPerson(personDTO);
            customResponse.setData(true);
            customResponse.setCode(200);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
            return customResponse;
        }
        return customResponse;
    }

    @PostMapping("/get/{authorId}")
    @ApiOperation(value="获取关系网")
    public CustomResponse addPerson(@PathVariable String authorId) {
        CustomResponse customResponse = new CustomResponse();
        try {
            customResponse.setData(personService.getCoauthors(authorId));
            customResponse.setCode(200);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
            return customResponse;
        }
        return customResponse;
    }

//
//    @PostMapping("/delById")
//    @ApiOperation(value="根据主键删除")
//    @ApiImplicitParam(name="id",value="主键",paramType="form")
//    public CustomResponse delById(Long id) {
//        CustomResponse customResponse = new CustomResponse();
//        personService.delById(id);
//        customResponse.setCode(200);
//        return customResponse;
//    }
//
//    @PostMapping("/updatePersone")
//    @ApiOperation(value="修改节点信息")
//    public CustomResponse updatePersone(Person Person) {
//        CustomResponse customResponse = new CustomResponse();
//        customResponse.setData(personService.updateById(Person));
//        return customResponse;
//    }
//
//    @GetMapping("/getInfoById")
//    @ApiOperation(value="根据主键查询")
//    @ApiImplicitParam(name="id",value="主键",paramType="form")
//    public CustomResponse getInfoById(Long id) {
//        CustomResponse customResponse = new CustomResponse();
//        System.out.println(id);
//        customResponse.setData(personService.getInfoById(id));
//        System.out.println("next");
//        return customResponse;
//    }
//
//    @GetMapping("/getAllRelationTypes")
//    @ApiOperation(value="获取所有的关系类型")
//    public CustomResponse getAllRelationTypes() {
//        CustomResponse customResponse = new CustomResponse();
//        customResponse.setData(personService.getAllRealationTypes());
//        return customResponse;
//    }
//
//    @PostMapping("/addDMRelationShip")
//    @ApiOperation(value="指定两个节点的关系(两个节点须存在)")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="name",value="对象名：唐三-",paramType="form"),
//            @ApiImplicitParam(name="relation",value="关系[父亲]->",paramType="form"),
//            @ApiImplicitParam(name="to",value="对象名：唐昊",paramType = "form")
//    })
//    public CustomResponse addDMRelationShip(String name,String relation,String to) {
//        //直接指定关系
//        CustomResponse customResponse = new CustomResponse();
//        personService.createRelation(name, relation, to);
//        return customResponse;
//    }
//
//    @GetMapping("/getRelationsByName")
//    @ApiOperation(value="获取指定节点指定关系信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="name",value="对象名：唐三",paramType="form"),
//            @ApiImplicitParam(name="relation",value="具体关系",paramType = "form")
//    })
//    public CustomResponse getRelationsByName(String name,String relation) {
//        CustomResponse customResponse = new CustomResponse();
//        customResponse.setData(personService.getRelationsByName(name,relation));
//        return customResponse;
//    }

    @GetMapping("/gettest")
    public CustomResponse getTest() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData("test");
        personService.gettest();
        return customResponse;
    }
}

