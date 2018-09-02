package co.fatboa.backsystem.service.Impl;

import co.fatboa.backsystem.dao.ILinkDao;
import co.fatboa.backsystem.dao.ILinkGroupDao;
import co.fatboa.backsystem.domain.entity.Link;
import co.fatboa.backsystem.domain.entity.LinkGroup;
import co.fatboa.backsystem.domain.params.LinkGroupParam;
import co.fatboa.backsystem.service.ILinkGroupService;
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
 * @Date: 2018/9/2 16:35
 * @Description: 链接组服务实现类
 * @Modified By:
 * @Version 1.0
 */
@Service
public class LinkGroupService implements ILinkGroupService {
    @Autowired
    private ILinkGroupDao linkGroupDao;
    @Autowired
    private ILinkDao linkDao;

    /**
     * 新增
     *
     * @param group
     * @return
     */
    @Override

    public LinkGroup save(LinkGroup group) throws Exception {
        group.setId(null); //防止前端误传id
        this.linkGroupDao.save(group);
        return group;
    }

    /**
     * 单个查询
     *
     * @param params
     * @return
     */
    @Override
    public LinkGroup findOne(LinkGroupParam params) {
        return this.linkGroupDao.findOne(queryPackage(params));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public LinkGroup findById(String id) throws Exception {
        return this.linkGroupDao.findById(id);
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<LinkGroup> findByPage(LinkGroupParam params) {
        Integer pageIndex = 0;
        Integer pageSize = 10;
        Query query = queryPackage(params);
        Pageable pageable;
        if (params.getPageIndex() != null) {
            pageIndex = params.getPageIndex();
        }
        if (params.getPageSize() != null) {
            pageSize = params.getPageSize();
        }
        pageable = new PageRequest(pageIndex, pageSize);
        long count = this.linkGroupDao.count(query);
        List<LinkGroup> linkGroups = this.linkGroupDao.findAll(query.with(pageable));
        return new PageImpl<LinkGroup>(linkGroups, pageable, count);
    }

    /**
     * 参数查询
     *
     * @param params
     * @return
     */
    @Override
    public List<LinkGroup> findAll(LinkGroupParam params) {
        return this.linkGroupDao.findAll(queryPackage(params));
    }


    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void delete(String... ids) throws Exception {
        for (String id : ids) {
            this.delete(id);
        }
    }

    /**
     * 单删
     *
     * @param id
     */
    @Override
    public void delete(String id) throws Exception {
        List<Link> links = this.linkDao.findAll(Query.query(Criteria.where("group.$id").is(new ObjectId(id))));//查询链接组下所有链接
        for (Link link : links) {
            this.linkDao.delete(link.getId());//遍历删除所有链接
        }
        this.linkGroupDao.delete(id);//删除链接组
    }

    /**
     * 更新
     *
     * @param group
     */
    @Override
    public void update(LinkGroup group) throws Exception {
        Query query = new Query();
        if (group.getId() != null && !group.getId().trim().isEmpty()) {
            if (this.linkGroupDao.findById(group.getId()) == null) {
                throw new Exception("不存在id=" + group.getId() + "的链接组，无法更新");
            }
            query.addCriteria(Criteria.where("id").is(new ObjectId(group.getId())));
        } else {
            throw new Exception("更新操作，链接组id不能为空");
        }
        Update update = new Update();
        if (group.getName() != null) {
            update.set("name", group.getName());
        }
        this.linkGroupDao.update(query, update);
    }

    /**
     * 封装query
     *
     * @param param
     * @return
     */
    Query queryPackage(LinkGroupParam param) {
        Query query = new Query();
        if (param.getId() != null) {
            query.addCriteria(Criteria.where("id").is(new ObjectId(param.getId())));
        }
        if (param.getName() != null) {
            query.addCriteria(Criteria.where("name").regex(param.getName()));
        }
        if (param.getSortDirection() != null && param.getSortFiled() != null) {
            if (param.getSortDirection().equals("asc")) {
                query.with(new Sort(Sort.Direction.ASC, param.getSortFiled()));
            } else if (param.getSortDirection().equals("desc")) {
                query.with(new Sort(Sort.Direction.DESC, param.getSortFiled()));
            }
        }
        return query;
    }
}
