package co.fatboa.myportal.dao;

/*
 * @author hl
 * @date 2018/8/22 16:43
 * @description  通用基本操作方法接口
 * @param
 * @return
 */

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {

    T save(T entity);

    void delete(Serializable... ids);

    List<T> findAll(Query query);

    T findOne(Query query);

    void update(Query query, Update update);
}
