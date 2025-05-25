package com.flash.achievements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.achievements.converter.WorkConverter;
import com.flash.achievements.dao.*;
import com.flash.achievements.dao.Source;
import com.flash.achievements.dto.*;
import com.flash.achievements.mapper.*;
import com.flash.achievements.service.WorkService;
import com.flash.achievements.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 11:25
 */

@Slf4j
@Service
public class WorkServiceImp implements WorkService {
    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private InstitutionMapper institutionMapper;

    @Autowired
    private ReferenceMapper referenceMapper;

    @Autowired
    private WorkAuthorShipMapper workAuthorShipMapper;

    @Autowired
    private WorkInstitutionShipMapper workInstitutionShipMapper;

    @Autowired
    private WorkConceptShipMapper workConceptShipMapper;

    @Autowired
    private WorkSourceShipMapper workSourceShipMapper;

    @Autowired
    private WorkRelateShipMapper workRelateShipMapper;

    @Autowired
    private WorkReferencedShipMapper workReferencedShipMapper;

    @Autowired
    private SourceMapper sourceMapper;
    @Autowired
    private ConceptMapper conceptMapper;


    @Override
    public Work addDocument(DocumentAdd documentAdd) throws BusinessException {
        String workId = documentAdd.getWorkId();
        List<AuthorDTO> authorDTOS = documentAdd.getAuthorDTOS();
        List<InstitutionDTO> institutionDTOS = documentAdd.getInstitutionDTOS();
        String title = documentAdd.getTitle();
        String abstracts = documentAdd.getAbstracts();
        List<ConceptDTO> conceptDTOS = documentAdd.getConceptDTOS();
        Integer cited = documentAdd.getCited();
        String type = documentAdd.getType();
        String doi = documentAdd.getDoi();
        Date publishTime = documentAdd.getPublishTime();
        List<SourceDTO> sources= documentAdd.getSources();
        List<RelatedDTO> relatedDTOS = documentAdd.getRelatedDTOS();
        List<ReferencesDTO> referencesDTOS = documentAdd.getReferencesDTOS();
        if (authorDTOS.isEmpty()){
            throw new BusinessException(400, "作者不存在");
        }
        if (title == null){
            throw new BusinessException(400, "论文题目为空");
        }
        if (sources.isEmpty()){
            throw  new BusinessException(400, "论文链接为空");
        }
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", workId);
        Work work = workMapper.selectOne(queryWrapper);
        if (work != null){
            throw new BusinessException(400, "论文重复");
        }
        Work newWork = new Work(workId, title, abstracts, cited, doi, publishTime, 0, type);
        workMapper.insert(newWork);
        for (AuthorDTO authorDTO : authorDTOS){
            String authorId = authorDTO.getAuthorId();
            WorkAuthorShip workAuthorShip = new WorkAuthorShip(workId, authorId);
            workAuthorShipMapper.insert(workAuthorShip);
        }

        for (InstitutionDTO institutionDTO : institutionDTOS){
            String institutionId = institutionDTO.getInstitutionId();
            WorkInstitutionShip workInstitutionShip = new WorkInstitutionShip(workId, institutionId);
            workInstitutionShipMapper.insert(workInstitutionShip);
        }

        for (ConceptDTO conceptDTO : conceptDTOS){
            String conceptId = conceptDTO.getConceptId();
            WorkConceptShip workConceptShip = new WorkConceptShip(workId, conceptId);
            workConceptShipMapper.insert(workConceptShip);
        }

        for (SourceDTO sourceDTO : sources){
            String sourceId = sourceDTO.getId();
            WorkSourceShip workSourceShip = new WorkSourceShip(workId, sourceId);
            workSourceShipMapper.insert(workSourceShip);
        }

        for (RelatedDTO relatedDTO : relatedDTOS){
            String relateId = relatedDTO.getId();
            WorkRelateShip workRelateShip = new WorkRelateShip(workId, relateId);
            workRelateShipMapper.insert(workRelateShip);
        }

        for (ReferencesDTO referencesDTO : referencesDTOS){
            String referenceId = referencesDTO.getId();
            WorkReferencedShip workReferencedShip = new WorkReferencedShip(workId, referenceId);
            workReferencedShipMapper.insert(workReferencedShip);
        }
        return newWork;
    }

    @Override
    public void deleteDocument(Integer documentId) throws BusinessException {
        if (documentId == null){
            throw new BusinessException(400, "论文不存在");
        }
        workMapper.deleteById(documentId);
        QueryWrapper<Records> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("have_id",documentId);
        queryWrapper.eq("type", 1);
        Records record = recordMapper.selectOne(queryWrapper);
        recordMapper.deleteById(record.getId());
    }

    @Override
    public List<Work> getAllDocuments() throws BusinessException {
        try {
            return workMapper.selectList(null);
        } catch (Exception e) {
            // 捕获异常并抛出自定义业务异常
            throw new BusinessException(400, "查询论文失败");
        }
    }


    @Override
    public DocumentDTO getDocumentById(String workId) throws BusinessException {
        if (workId == null){
            throw new BusinessException(400, "论文不存在");
        }
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", workId);
        Work work = workMapper.selectById(workId);
        List<AuthorInfo> authorInfos = new ArrayList<>();
        List<SchoolInfo> schoolInfos = new ArrayList<>();
        List<SourceDTO> sourceDTOS = new ArrayList<>();
        List<ConceptDTO> conceptDTOS = new ArrayList<>();
        QueryWrapper<WorkAuthorShip> workAuthorShipQueryWrapper = new QueryWrapper<>();
        workAuthorShipQueryWrapper.eq("work_id", work.getId());
        List<WorkAuthorShip> workAuthorShips = workAuthorShipMapper.selectList(workAuthorShipQueryWrapper);
        for (WorkAuthorShip workAuthorShip : workAuthorShips){
            Author author = authorMapper.selectById(workAuthorShip.getAuthorId());
            AuthorInfo authorInfo = new AuthorInfo(author.getId(), author.getName(), author.getOrcid());
            authorInfos.add(authorInfo);
        }
        QueryWrapper<WorkInstitutionShip> workInstitutionShipQueryWrapper = new QueryWrapper<>();
        workInstitutionShipQueryWrapper.eq("work_id", work.getId());
        List<WorkInstitutionShip> workInstitutionShips = workInstitutionShipMapper.selectList(workInstitutionShipQueryWrapper);
        for (WorkInstitutionShip workInstitutionShip : workInstitutionShips){
            Institution institution = institutionMapper.selectById(workInstitutionShip.getInstitutionId());
            SchoolInfo schoolInfo = new SchoolInfo(institution.getId(), institution.getName());
            schoolInfos.add(schoolInfo);
        }

        QueryWrapper<WorkSourceShip> workSourceShipQueryWrapper = new QueryWrapper<>();
        workSourceShipQueryWrapper.eq("work_id", work.getId());
        List<WorkSourceShip> workSourceShips = workSourceShipMapper.selectList(workSourceShipQueryWrapper);
        for (WorkSourceShip workSourceShip : workSourceShips){
            Source source = sourceMapper.selectById(workSourceShip.getSourceId());
            SourceDTO sourceDTO = new SourceDTO(source.getIsOa(), source.getOaUrl(), source.getId(), source.getOaName());
            sourceDTOS.add(sourceDTO);
        }

        QueryWrapper<WorkConceptShip> workConceptShipQueryWrapper = new QueryWrapper<>();
        workConceptShipQueryWrapper.eq("work_id", work.getId());
        List<WorkConceptShip> workConceptShips = workConceptShipMapper.selectList(workConceptShipQueryWrapper);
        for (WorkConceptShip workConceptShip : workConceptShips){
            Concept concept = conceptMapper.selectById(workConceptShip.getConceptId());
            ConceptDTO conceptDTO = new ConceptDTO(concept.getId(), concept.getName(), concept.getLevel());
            conceptDTOS.add(conceptDTO);
        }
        return WorkConverter.documentConverter(work, authorInfos, schoolInfos, sourceDTOS, conceptDTOS);
    }

    @Override
    public List<DocumentDTO> searchDocument(String keyWords) throws BusinessException {
        if (keyWords == null || keyWords.trim().isEmpty()) {
            throw new BusinessException(400, "关键字不能为空");
        }
        // 使用 MyBatis-Plus 条件构造器实现模糊查询
        LambdaQueryWrapper<Work> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Work::getTitle, keyWords.trim());
        List<Work> works = workMapper.selectList(queryWrapper);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (Work work : works){
            List<AuthorInfo> authorInfos = new ArrayList<>();
            List<SchoolInfo> schoolInfos = new ArrayList<>();
            List<SourceDTO> sourceDTOS = new ArrayList<>();
            List<ConceptDTO> conceptDTOS = new ArrayList<>();
            QueryWrapper<WorkAuthorShip> workAuthorShipQueryWrapper = new QueryWrapper<>();
            workAuthorShipQueryWrapper.eq("work_id", work.getId());
            List<WorkAuthorShip> workAuthorShips = workAuthorShipMapper.selectList(workAuthorShipQueryWrapper);
            for (WorkAuthorShip workAuthorShip : workAuthorShips){
                Author author = authorMapper.selectById(workAuthorShip.getAuthorId());
                AuthorInfo authorInfo = new AuthorInfo(author.getId(), author.getName(), author.getOrcid());
                authorInfos.add(authorInfo);
            }
            QueryWrapper<WorkInstitutionShip> workInstitutionShipQueryWrapper = new QueryWrapper<>();
            workInstitutionShipQueryWrapper.eq("work_id", work.getId());
            List<WorkInstitutionShip> workInstitutionShips = workInstitutionShipMapper.selectList(workInstitutionShipQueryWrapper);
            for (WorkInstitutionShip workInstitutionShip : workInstitutionShips){
                Institution institution = institutionMapper.selectById(workInstitutionShip.getInstitutionId());
                SchoolInfo schoolInfo = new SchoolInfo(institution.getId(), institution.getName());
                schoolInfos.add(schoolInfo);
            }

            QueryWrapper<WorkSourceShip> workSourceShipQueryWrapper = new QueryWrapper<>();
            workSourceShipQueryWrapper.eq("work_id", work.getId());
            List<WorkSourceShip> workSourceShips = workSourceShipMapper.selectList(workSourceShipQueryWrapper);
            for (WorkSourceShip workSourceShip : workSourceShips){
                Source source = sourceMapper.selectById(workSourceShip.getSourceId());
                SourceDTO sourceDTO = new SourceDTO(source.getIsOa(), source.getOaUrl(), source.getId(), source.getOaName());
                sourceDTOS.add(sourceDTO);
            }

            QueryWrapper<WorkConceptShip> workConceptShipQueryWrapper = new QueryWrapper<>();
            workConceptShipQueryWrapper.eq("work_id", work.getId());
            List<WorkConceptShip> workConceptShips = workConceptShipMapper.selectList(workConceptShipQueryWrapper);
            for (WorkConceptShip workConceptShip : workConceptShips){
                Concept concept = conceptMapper.selectById(workConceptShip.getConceptId());
                ConceptDTO conceptDTO = new ConceptDTO(concept.getId(), concept.getName(), concept.getLevel());
                conceptDTOS.add(conceptDTO);
            }
            documentDTOS.add(WorkConverter.documentConverter(work, authorInfos, schoolInfos, sourceDTOS, conceptDTOS));
        }
        return documentDTOS;
    }

    @Override
    public List<DocumentDTO> getReference(Integer documentId) throws BusinessException {
        if (documentId == null){
            throw new BusinessException(400, "关键字不能为空");
        }
        QueryWrapper<Reference> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("main_id", documentId);
        List<Reference> references = referenceMapper.selectList(queryWrapper);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
//        for (Reference reference : references){
//            QueryWrapper<Document> documentQueryWrapper = new QueryWrapper<>();
//            documentQueryWrapper.eq("document_id", reference.getReferId());
//            Document document = documentMapper.selectOne(documentQueryWrapper);
//            List<AuthorInfo> authorInfos = new ArrayList<>();
//            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//            userQueryWrapper.eq("uid", document.getAuthorId());
//            User user = userMapper.selectOne(userQueryWrapper);
//            AuthorInfo authorInfo = new AuthorInfo(user.getUid(), user.getName(), user.getOid());
//            authorInfos.add(authorInfo);
//            documentDTOS.add(DocumentConverter.documentConverter(document, authorInfos, new ArrayList<>()));
//        }
        return documentDTOS;
    }

    @Override
    public List<DocumentDTO> getReferenced(Integer documentId) throws BusinessException {
        if (documentId == null){
            throw new BusinessException(400, "关键字不能为空");
        }
        QueryWrapper<Reference> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("refer_id", documentId);
        List<Reference> references = referenceMapper.selectList(queryWrapper);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
//        for (Reference reference : references){
//            QueryWrapper<Document> documentQueryWrapper = new QueryWrapper<>();
//            documentQueryWrapper.eq("document_id", reference.getMainId());
//            Document document = documentMapper.selectOne(documentQueryWrapper);
//            List<AuthorInfo> authorInfos = new ArrayList<>();
//            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//            userQueryWrapper.eq("uid", document.getAuthorId());
//            User user = userMapper.selectOne(userQueryWrapper);
//            AuthorInfo authorInfo = new AuthorInfo(user.getUid(), user.getName(), user.getOid());
//            authorInfos.add(authorInfo);
//            documentDTOS.add(DocumentConverter.documentConverter(document, authorInfos, new ArrayList<>()));
//        }
        return documentDTOS;
    }



}

