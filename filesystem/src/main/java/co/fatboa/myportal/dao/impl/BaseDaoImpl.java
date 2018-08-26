package co.fatboa.myportal.dao.impl;

import co.fatboa.myportal.dao.IBaseDao;
import co.fatboa.myportal.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 16:46 2018/8/22
 * @Modified By:
 * @Version 1.0
 */
@Repository
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
    protected abstract Class<T> getEnityClass();


    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public T save(T entity) {
        mongoTemplate.save(entity);
        return entity;
    }

    @Override
    public void delete(Serializable... ids) {
        if (!EmptyUtil.isEmpty(ids)) {
            for (Serializable id : ids) {
                this.mongoTemplate.remove(this.mongoTemplate.findById(id, getEnityClass()));
            }

        }
    }

    @Override
    public T findOne(Query query) {
        return this.mongoTemplate.findOne(query, getEnityClass());
    }

    @Override
    public List<T> findAll(Query query) {
        return this.mongoTemplate.find(query, getEnityClass());
    }

    @Override
    public void update(Query query, Update update) {
//        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
//        String json = gson.toJson(entity);
//        Type type = new TypeToken<Map<String, Object>>() {
//        }.getType();
//        Map<String, Object> map = gson.fromJson(json, type);
//        Object id = map.get("id");
//        map.remove("id");
//        Update update = new Update();
//        for (String key : map.keySet()) {
//            update.set(key, map.get(key));
//        }
        this.mongoTemplate.upsert(query, update, getEnityClass());
    }

}
