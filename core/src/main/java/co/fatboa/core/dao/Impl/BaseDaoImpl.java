package co.fatboa.core.dao.Impl;

import co.fatboa.core.dao.IBaseDao;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/8/31 19:32
 * @Description: mongo数据访问泛型抽象类
 * @Modified By:
 * @Version 1.0
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
    @Autowired
    private MongoTemplate mongoTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取泛型参数的类
     *
     * @return
     */
    protected abstract Class<T> getEntityClass();

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @Override
    public T save(T entity) {
        this.mongoTemplate.save(entity);
        logger.info("保存成功" + entity.toString());
        return entity;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void delete(Serializable... ids) throws Exception {
        for (Serializable id : ids) {
            delete(id);
        }

    }

    /**
     * 单删
     *
     * @param id
     */
    @Override
    public void delete(Serializable id) throws Exception {
        if (id != null) {
            T entity = this.mongoTemplate.findById(id, getEntityClass());
            if (entity == null) {
                throw new Exception(id.toString());
            }
            this.mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), getEntityClass());
        }
    }

    /**
     * 多个查询
     *
     * @param query
     * @return
     */
    @Override
    public List<T> findAll(Query query) {
        return this.mongoTemplate.find(query, getEntityClass());
    }

    /**
     * 单个查询
     *
     * @param query
     * @return
     */
    @Override
    public T findOne(Query query) {
        return this.mongoTemplate.findOne(query, getEntityClass());
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public T findById(Serializable id) {
        return this.mongoTemplate.findById(id, getEntityClass());
    }

    /**
     * 更新
     *
     * @param query
     * @param update
     */
    @Override
    public void update(Query query, Update update) {
        this.mongoTemplate.upsert(query, update, getEntityClass());
    }

    /**
     * 查询结果个数
     *
     * @param query
     * @return
     */
    @Override
    public long count(Query query) {
        return this.mongoTemplate.count(query, getEntityClass());
    }


}
