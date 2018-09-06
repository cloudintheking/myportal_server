package co.fatboa.backsystem.service.Impl;

import co.fatboa.backsystem.dao.IArticleDao;
import co.fatboa.backsystem.dao.ICategoryDao;
import co.fatboa.backsystem.dao.IZoneDao;
import co.fatboa.backsystem.domain.dto.CategoryDto;
import co.fatboa.backsystem.domain.entity.Article;
import co.fatboa.backsystem.domain.entity.Category;
import co.fatboa.backsystem.domain.entity.Zone;
import co.fatboa.backsystem.domain.mapper.CategoryMapper;
import co.fatboa.backsystem.domain.params.CategoryParam;
import co.fatboa.backsystem.service.ICategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Auther: hl
 * @Date: 2018/9/1 10:35
 * @Description: 栏目服务接口实现
 * @Modified By:
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IArticleDao articleDao;
    @Autowired
    private IZoneDao zoneDao;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增
     *
     * @param categoryDto
     * @return
     */
    @Override
    public CategoryDto save(CategoryDto categoryDto) throws Exception {
        Category category = this.categoryMapper.to(categoryDto);
        if (categoryDto.getParent() == null || categoryDto.getParent().trim().isEmpty()) {
            category.setLevel(1); //若parentId为null或空字符串，则默认为1级栏目
            category.setParent(null);
        } else {
            Category parent = this.categoryDao.findById(categoryDto.getParent().trim());
            if (parent == null) {
                throw new Exception("不存在该父级栏目id:" + categoryDto.getParent().trim());
            }
            category.setLevel(parent.getLevel() + 1);
            category.setParent(parent);
        }
        category.setId(null);//防止前端误传id
        this.categoryDao.save(category);
        return this.categoryMapper.from(category);
    }

    /**
     * 根据ID查询 树形结构
     *
     * @param id         栏目id
     * @param showChilds 是否显示子级栏目
     * @param deep       栏目深度
     * @param byShow     是否按栏目显示状态查询
     * @return
     */
    @Override
    public Category findById(String id, Boolean showChilds, Integer deep, Boolean byShow) throws Exception {
        Category category = this.findById(id);
        if (category == null) {
            throw new Exception("找不到id=" + id + "的栏目");
        }
        if (showChilds) {
            category.setChilds(getChilds(category.getId(), deep, byShow));
        }
        return category;
    }

    /**
     * 栏目树形结构
     *
     * @param deep   树的深度
     * @param byShow 是否按栏目显示状态查询
     * @return
     */
    @Override
    public List<Category> findByTree(Integer deep, Boolean byShow) {
        Query query = new Query();
        query.addCriteria(Criteria.where("level").is(1)); //查找一级栏目
        if (byShow) {
            query.addCriteria(Criteria.where("show").is(true));//只查找显示状态为true的栏目
        }
        List<Category> parents = this.categoryDao.findAll(query);
        for (Category p : parents) {
            p.setChilds(getChilds(p.getId(), deep, byShow));
        }
        return parents;
    }


    /**
     * 单个查询
     *
     * @param params
     * @return
     */
    @Override
    public Category findOne(CategoryParam params) {
        Query query = queryPackage(params);
        Category categorie = this.categoryDao.findOne(query);
        return categorie;
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Category findById(String id) throws Exception {
        return this.categoryDao.findById(id);
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<Category> findByPage(CategoryParam params) {
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
        long count = this.categoryDao.count(query);
        List<Category> categories = this.categoryDao.findAll(query.with(pageable));
        return new PageImpl<Category>(categories, pageable, count);
    }

    /**
     * 参数查询
     *
     * @param params
     * @return
     */
    @Override
    public List<Category> findAll(CategoryParam params) {
        Query query = queryPackage(params);
        return this.categoryDao.findAll(query);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(String... ids) throws Exception {
        for (String id : ids) {
            this.delete(id);
        }
    }

    /**
     * 删除栏目的子级栏目自动升级
     *
     * @param id         删除栏目的id
     * @param deleA      是否删除被关联的文章
     * @param deleZ      是否删除被关联的首页展区
     * @param articleref 如果不删除被关联文章,文章关联到指定栏目下
     * @param zoneref    如果不删除首页展区,首页展区关联到指定栏目下
     */
    @Override
    public void delete(String id, Boolean deleA, Boolean deleZ, String articleref, String zoneref) throws Exception {
        Category category = this.categoryDao.findById(id);
        if (category == null) {
            throw new Exception("不存在该id=" + id + "的栏目，无法删除");
        }
        Category newParent = category.getParent();

        List<Category> childs = getChilds(category.getId(), 2, false);//获取子级
        for (Category c : childs) {
            c.setParent(newParent);//设置新父级
            this.update(this.categoryMapper.from(c));//更新自己的level
            childLevelUpdate(c);//下级level递归刷新
        }
        Query query = Query.query(Criteria.where("category.$id").is(new ObjectId(id)));
        List<Article> articles = this.articleDao.findAll(query);
        for (Article a : articles) {
            if (deleA) {
                this.articleDao.delete(a.getId()); //删除关联文章
            } else {
                a.setCategory(this.findById(articleref));
                this.articleDao.save(a);//关联新的栏目
            }
        }
        List<Zone> zones = this.zoneDao.findAll(query);
        for (Zone z : zones) {
            if (deleZ) {
                this.zoneDao.delete(z.getId());//删除关联首页展区
            } else {
                z.setCategory(this.findById(zoneref));
                this.zoneDao.save(z);//关联新的栏目
            }
        }
        this.delete(id);//删除栏目
    }


    /**
     * 单删
     *
     * @param id
     */

    @Override
    public void delete(String id) throws Exception {
        this.categoryDao.delete(id);
    }

    /**
     * 更新
     *
     * @param categoryDto
     */
    @Override
    public void update(CategoryDto categoryDto) throws Exception {
        Query query = new Query();

        if (categoryDto.getId() != null && !categoryDto.getId().trim().isEmpty()) {
            if (this.categoryDao.findById(categoryDto.getId()) == null) {
                throw new Exception("不存在该栏目id:" + categoryDto.getId() + ",无法更新");
            }
            query.addCriteria(Criteria.where("id").is(categoryDto.getId()));
        } else {
            throw new Exception("更新栏目请务必上传栏目id");
        }
        Update update = new Update();
        if (categoryDto.getParent() != null && !categoryDto.getParent().trim().isEmpty()) {
            Category parent = this.categoryDao.findById(categoryDto.getParent());
            if (parent == null) {
                throw new Exception("不存在该父级栏目id:" + categoryDto.getParent());
            }
            update.set("parent", parent);
            update.set("level", parent.getLevel() + 1);
        } else {
            update.set("parent", null);
            update.set("level", 1);
        }
        if (categoryDto.getName() != null) {
            update.set("name", categoryDto.getName());
        }
        if (categoryDto.getRoute() != null) {
            update.set("route", categoryDto.getRoute());
        }
        if (categoryDto.getShow() != null) {
            update.set("show", categoryDto.getShow());
        }
        if (categoryDto.getStyle() != null) {
            update.set("style", categoryDto.getStyle());
        }
        this.categoryDao.update(query, update);

        childLevelUpdate(this.categoryDao.findById(categoryDto.getId())); // 刷新子级栏目level

    }

    /**
     * 封装query
     *
     * @param params
     * @return
     */
    public Query queryPackage(CategoryParam params) {
        Query query = new Query();
        Sort sort;

        Criteria criteria = new Criteria();
        if (params.getId() != null) {
            criteria.and("id").is(params.getId());
        }
        if (params.getLevel() != null) {
            criteria.and("level").is(params.getLevel());
        }
        if (params.getName() != null) {
            criteria.and("name").regex(params.getName());
        }
        if (params.getShow() != null) {
            criteria.and("show").is(params.getShow());
        }
        if (params.getStyle() != null) {
            criteria.and("style").is(params.getStyle());
        }

        if (params.getSortField() != null && params.getSortOrder() != null) {
            if (params.getSortOrder().equals("asc")) {
                sort = new Sort(Sort.Direction.ASC, params.getSortField());
                query.with(sort);
            } else if (params.getSortOrder().equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, params.getSortField());
                query.with(sort);
            }
        }
        query.addCriteria(criteria);
        return query;
    }

    /**
     * 子级栏目level递归更新
     *
     * @param parent
     */
    void childLevelUpdate(Category parent) {
        Query query = new Query().addCriteria(Criteria.where("parent.$id").is(new ObjectId(parent.getId())));
        List<Category> childs = this.categoryDao.findAll(query);
        if (childs != null) {
            for (Category category : childs) {
                category.setLevel(parent.getLevel() + 1);
                this.categoryDao.save(category);
                childLevelUpdate(category);
            }
        }
    }

    /**
     * @param pid    父级栏目Id
     * @param deep   递归深度
     * @param byShow 是否按栏目显示状态查询
     * @return
     */
    List<Category> getChilds(String pid, Integer deep, Boolean byShow) {
        List<Category> categories = null;
        if (deep > 1) {
            Query query = new Query();
            query.addCriteria(Criteria.where("parent.$id").is(new ObjectId(pid)));
            if (byShow) {
                query.addCriteria(Criteria.where("show").is(true));
            }
            categories = this.categoryDao.findAll(query);
            if (categories != null) {
                for (Category category : categories) {
                    category.setChilds(getChilds(category.getId(), deep - 1, byShow));
                }
            }
        }
        return categories;
    }
}
