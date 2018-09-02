package co.fatboa.backsystem.service.Impl;

import co.fatboa.backsystem.dao.ILinkDao;
import co.fatboa.backsystem.dao.ILinkGroupDao;
import co.fatboa.backsystem.domain.dto.LinkDto;
import co.fatboa.backsystem.domain.entity.Link;
import co.fatboa.backsystem.domain.entity.LinkGroup;
import co.fatboa.backsystem.domain.mapper.LinkMapper;
import co.fatboa.backsystem.domain.params.LinkParam;
import co.fatboa.backsystem.service.ILinkService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/2 17:13
 * @Description: 链接服务实现类
 * @Modified By:
 * @Version 1.0
 */
@Service
public class LinkService implements ILinkService {
    @Autowired
    private ILinkDao linkDao;
    @Autowired
    private ILinkGroupDao linkGroupDao;
    @Autowired
    private LinkMapper linkMapper;

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @Override
    public LinkDto save(LinkDto dto) throws Exception {
        Link link = this.linkMapper.to(dto);
        if (dto.getGroup() == null || dto.getGroup().trim().isEmpty()) {
            throw new Exception("新增链接时，关联链接组id不能为空");
        } else {
            LinkGroup group = this.linkGroupDao.findById(dto.getGroup().trim());
            if (group == null) {
                throw new Exception("不存在id=" + "的链接组，无法新增链接");
            }
            link.setGroup(group);
        }
        link.setId(null);//防止前端误传id
        this.linkDao.save(link);
        return this.linkMapper.from(link);
    }

    /**
     * 单个查询
     *
     * @param params
     * @return
     */
    @Override
    public Link findOne(LinkParam params) {
        return this.linkDao.findOne(queryPackage(params));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Link findById(String id) throws Exception {
        Link link = this.linkDao.findById(id);
        if (link == null) {
            throw new Exception("找不到id=" + id + "的链接");
        }
        return link;
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<Link> findByPage(LinkParam params) {
        Integer pageIndex = 0;
        Integer pageSize = 10;
        Query query = queryPackage(params);
        if (params.getPageIndex() != null) {
            pageIndex = params.getPageIndex();
        }
        if (params.getPageSize() != null) {
            pageSize = params.getPageSize();
        }
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        long count = this.linkDao.count(query);
        List<Link> links = this.linkDao.findAll(query.with(pageable));
        return new PageImpl<Link>(links, pageable, count);
    }

    /**
     * 参数查询
     *
     * @param params
     * @return
     */
    @Override
    public List<Link> findAll(LinkParam params) {
        return this.linkDao.findAll(queryPackage(params));
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void delete(String... ids) throws Exception {
        this.linkDao.delete(ids);
    }

    /**
     * 单删
     *
     * @param id
     */
    @Override
    public void delete(String id) throws Exception {
        this.linkDao.delete(id);
    }

    /**
     * 更新
     *
     * @param dto
     */
    @Override
    public void update(LinkDto dto) throws Exception {
        Query query = new Query();
        if (dto.getId() != null && !dto.getId().trim().isEmpty()) {
            if (this.linkDao.findById(dto.getId().trim()) == null) {
                throw new Exception("不存在id=" + dto.getId() + "的链接,无法更新");
            }
            query.addCriteria(Criteria.where("id").is(new ObjectId(dto.getId().trim())));
        } else {
            throw new Exception("更新链接时，链接id不能为空");
        }
        Update update = new Update();
        if (dto.getGroup() != null && !dto.getGroup().trim().isEmpty()) {
            if (this.linkGroupDao.findById(dto.getGroup().trim()) == null) {
                throw new Exception("不存在id=" + dto.getGroup().trim() + "的链接组id，无法更新");
            }
            update.set("group.$id", dto.getGroup().trim());
        } else {
            throw new Exception("更新链接时,关联链接组id不能为空");
        }
        if (dto.getName() != null) {
            update.set("name", dto.getName());
        }
        if (dto.getUrl() != null) {
            update.set("url", dto.getUrl());
        }
        this.linkDao.update(query, update);
    }

    /**
     * query封装
     *
     * @param param
     * @return
     */
    Query queryPackage(LinkParam param) {
        Query query = new Query();
        Sort sort = null;
        Criteria criteria = new Criteria();
        if (param.getId() != null) {
            criteria.and("id").is(new ObjectId(param.getId()));
        }
        if (param.getGroup() != null && !param.getGroup().trim().isEmpty()) {
            criteria.and("group.$id").is(new ObjectId(param.getGroup().trim()));
        }
        if (param.getName() != null) {
            criteria.and("name").regex(param.getName());
        }
        if (param.getSortDirection() != null && param.getSortFiled() != null) {
            if (param.getSortDirection().equals("asc")) {
                sort = new Sort(Sort.Direction.ASC, param.getSortFiled());
                query.with(sort);
            } else if (param.getSortDirection().equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, param.getSortFiled());
                query.with(sort);
            }
        }
        return query;
    }
}
