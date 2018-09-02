package co.fatboa.core.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.List;

/**
 * mongo数据访问泛型接口
 */
public interface IBaseDao<T> {
    /**
     * 新增
     *
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Serializable... ids) throws Exception;
    
    /**
     * 多个查询
     *
     * @param query
     * @return
     */
    List<T> findAll(Query query);

    /**
     * 单个查询
     *
     * @param query
     * @return
     */
    T findOne(Query query);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T findById(Serializable id);

    /**
     * 更新
     *
     * @param query
     * @param update
     */
    void update(Query query, Update update);

    /**
     * 查询结果个数
     * @param query
     * @return
     */
    long count(Query query);
}
