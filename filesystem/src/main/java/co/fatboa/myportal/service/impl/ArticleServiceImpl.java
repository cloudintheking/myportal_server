package co.fatboa.myportal.service.impl;

import co.fatboa.myportal.dao.IArticleDao;
import co.fatboa.myportal.domain.entity.Article;
import co.fatboa.myportal.domain.params.ArticleParams;
import co.fatboa.myportal.service.ArticleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 9:36 2018/8/23
 * @Modified By:
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Override
    public Article save(Article entity) {
        return this.articleDao.save(entity);
    }

    @Override
    public void delete(Serializable... ids) {
        articleDao.delete(ids);
    }

    @Override
    public List<Article> findAll(ArticleParams articleParams) {

        return this.articleDao.findAll(new Query());
    }

    @Override
    public Article findOne(ArticleParams articleParams) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(articleParams.getId());

        query.addCriteria(criteria);
        return this.articleDao.findOne(query);
    }

    @Override
    public Article findById(String id) {

        return null;
    }

    @Override
    public void update(ArticleParams params) {
        Query query = new Query();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String json = gson.toJson(params);
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = gson.fromJson(json, type);
        if (map.get("id") != null) {
            query.addCriteria(Criteria.where("id").is(map.get("id")));
        }
        map.remove("id");

        Update update = new Update();
        for (String key : map.keySet()) {
            update.set(key, map.get(key));
        }
        this.articleDao.update(query, update);
        //this.articleDao.update(article);
    }
}
