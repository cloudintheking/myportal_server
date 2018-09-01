package co.fatboa.backsystem.service.Impl;

import co.fatboa.backsystem.dao.ICategoryDao;
import co.fatboa.backsystem.domain.dto.CategoryDto;
import co.fatboa.backsystem.domain.entity.Category;
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
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;
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
                throw new Exception("不存在该父级栏目id:" + categoryDto.getParent());
            }
            category.setLevel(parent.getLevel() + 1);
            category.setParent(parent);
        }
        category.setId(null);// 防止前台传ID
        this.categoryDao.save(category);
        return this.categoryMapper.from(category);
    }

    /**
     * 根据ID查询
     *
     * @param id         栏目id
     * @param showChilds 是否显示子级栏目
     * @param deep       栏目深度
     * @return
     */
    @Override
    public Category findById(String id, Boolean showChilds, Integer deep) throws Exception {
        Category category = this.findById(id);
        if (category == null) {
            throw new Exception("找不到id为" + id + "的栏目");
        }
        if (showChilds) {
            category.setChilds(getChilds(category.getId(), deep));
        }
        return category;
    }

    /**
     * 单个查询
     *
     * @param params
     * @return
     */
    @Override
    public Category findOne(CategoryParam params) {
        Query query = querypackage(params);
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
     * 批量查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<Category> findAll(CategoryParam params) {
        Integer pageIndex = 0;
        Integer pageSize = 10;
        Query query = querypackage(params);
        Pageable pageable;
        if (params.getPageIndex() != null) {
            pageIndex = params.getPageIndex();
        }
        if (params.getPageSize() != null) {
            pageSize = params.getPageSize();
        }
        pageable = new PageRequest(pageIndex, pageSize);
        long count = this.categoryDao.count(query);
        query.with(pageable);
        List<Category> categories = this.categoryDao.findAll(query);
        Page<Category> categoryPage = new PageImpl<Category>(categories, pageable, count);
        return categoryPage;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(String... ids) throws Exception {
        this.categoryDao.delete(ids);
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
            update.set("name", categoryDto.getRoute());
        }
        if (categoryDto.getShow() != null) {
            update.set("name", categoryDto.getShow());
        }
        if (categoryDto.getStyle() != null) {
            update.set("name", categoryDto.getStyle());
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
    public Query querypackage(CategoryParam params) {

        Query query = new Query();
        Sort sort;

        Criteria criteria = new Criteria();
        if (params.getId() != null) {
            criteria = criteria.and("id").is(params.getId());
        }
        if (params.getLevel() != null) {
            criteria = criteria.and("level").is(params.getLevel());
        }
        if (params.getName() != null) {
            criteria = criteria.and("name").regex(params.getName());
        }
        if (params.getShow() != null) {
            criteria = criteria.and("show").is(params.getShow());
        }

        if (params.getSortFiled() != null && params.getSortDirection() != null) {
            if (params.getSortDirection().equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, params.getSortFiled());
                query.with(sort);
            } else if (params.getSortDirection().equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, params.getSortFiled());
                query.with(sort);
            }

        }
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
     * @param pid  父级栏目Id
     * @param deep 递归深度
     * @return
     */
    List<Category> getChilds(String pid, Integer deep) {
        List<Category> categories = null;
        if (deep > 0) {
            categories = this.categoryDao.findAll(Query.query(Criteria.where("parent.$id").is(new ObjectId(pid))));
            if (categories != null) {
                for (Category category : categories) {
                    category.setChilds(getChilds(category.getId(), deep - 1));
                }
            }
        }
        return categories;
    }
}
