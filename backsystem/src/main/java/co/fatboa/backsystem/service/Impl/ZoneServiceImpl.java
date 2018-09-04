package co.fatboa.backsystem.service.Impl;

import co.fatboa.backsystem.dao.ICategoryDao;
import co.fatboa.backsystem.dao.IZoneDao;
import co.fatboa.backsystem.domain.dto.ZoneDto;
import co.fatboa.backsystem.domain.entity.Category;
import co.fatboa.backsystem.domain.entity.Zone;
import co.fatboa.backsystem.domain.mapper.ZoneMapper;
import co.fatboa.backsystem.domain.params.ZoneParam;
import co.fatboa.backsystem.service.IZoneService;
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
 * @Date: 2018/9/1 21:32
 * @Description: 首页展区服务实现类
 * @Modified By:
 * @Version 1.0
 */
@Service
public class ZoneServiceImpl implements IZoneService {
    @Autowired
    private IZoneDao zoneDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private ZoneMapper zoneMapper;

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @Override
    public ZoneDto save(ZoneDto dto) throws Exception {
        Zone zone = zoneMapper.to(dto);
        if (dto.getCategory() == null || dto.getCategory().trim().isEmpty()) {
            throw new Exception("新增首页展区时,必须关联栏目id");
        } else {
            Category category = this.categoryDao.findById(dto.getCategory().trim());
            if (category == null) {
                throw new Exception("关联栏目id:" + dto.getCategory().trim() + "不存在");
            }
            zone.setCategory(category);
        }
        zone.setId(null);//防止前端误传id
        this.zoneDao.save(zone);
        return this.zoneMapper.from(zone);
    }

    /**
     * 单个查询
     *
     * @param params
     * @return
     */
    @Override
    public Zone findOne(ZoneParam params) {
        Query query = queryPackage(params);
        return this.zoneDao.findOne(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Zone findById(String id) throws Exception {
        Zone zone = this.zoneDao.findById(id);
        if (zone == null) {
            throw new Exception("找不到id=" + id + "的首页展区");
        }
        return zone;
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<Zone> findByPage(ZoneParam params) {
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
        long count = this.zoneDao.count(query);
        List<Zone> zones = this.zoneDao.findAll(query.with(pageable));
        return new PageImpl<Zone>(zones, pageable, count);
    }

    /**
     * 参数查询
     *
     * @param params
     * @return
     */
    @Override
    public List<Zone> findAll(ZoneParam params) {
        return this.zoneDao.findAll(queryPackage(params));
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void delete(String... ids) throws Exception {
        this.zoneDao.delete(ids);
    }

    /**
     * 单删
     *
     * @param id
     */
    @Override
    public void delete(String id) throws Exception {
        this.zoneDao.delete(id);
    }

    /**
     * 更新
     *
     * @param dto
     */
    @Override
    public void update(ZoneDto dto) throws Exception {
        Query query = new Query();
        if (dto.getId() != null && !dto.getId().trim().isEmpty()) {
            if (this.zoneDao.findById(dto.getId()) == null) {
                throw new Exception("不存在该展区id:" + dto.getId() + ",无法更新");
            } else {
                query.addCriteria(Criteria.where("id").is(new ObjectId(dto.getId())));
            }
        } else {
            throw new Exception("更新首页展区,请务必上传展区id");
        }
        Update update = new Update();
        if (dto.getCategory() != null && !dto.getCategory().trim().isEmpty()) {
            Category category = this.categoryDao.findById(dto.getCategory());
            if (category == null) {
                throw new Exception("不存在该父级栏目id:" + dto.getCategory());
            }
            update.set("category", category);
        } else {
            throw new Exception("首页展区关联栏目id不能为空");
        }
        if (dto.getName() != null) {
            update.set("name", dto.getName());
        }
        if (dto.getPos() != null) {
            update.set("pos", dto.getPos());
        }
        if (dto.getShow() != null) {
            update.set("show", dto.getShow());
        }
        if (dto.getStyle() != null) {
            update.set("style", dto.getStyle());
        }
        if (dto.getWidth() != null) {
            update.set("width", dto.getWidth());
        }
        this.zoneDao.update(query, update);
    }

    /**
     * query封装
     *
     * @param param
     * @return
     */
    Query queryPackage(ZoneParam param) {
        Query query = new Query();
        Sort sort = null;
        Criteria criteria = new Criteria();
        if (param.getId() != null) {
            criteria.and("id").is(new ObjectId(param.getId()));
        }
        if (param.getCategory() != null && !param.getCategory().trim().isEmpty()) {
            criteria.and("category.$id").is(new ObjectId(param.getCategory().trim()));
        }
        if (param.getName() != null) {
            criteria.and("name").regex(param.getName());
        }
        if (param.getShow() != null) {
            criteria.and("show").is(param.getShow());
        }
        if (param.getStyle() != null) {
            criteria.and("style").is(param.getStyle());
        }
        if (param.getSortOrder() != null && param.getSortField() != null) {
            if (param.getSortOrder().equals("asc")) {
                sort = new Sort(Sort.Direction.ASC, param.getSortField());
                query.with(sort);
            } else if (param.getSortOrder().equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, param.getSortField());
                query.with(sort);
            }
        }
        query.addCriteria(criteria);
        return query;
    }
}
