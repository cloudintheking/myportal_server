package co.fatboa.filesystem.service.impl;

import co.fatboa.filesystem.dao.IArticleDao;
import co.fatboa.filesystem.domain.entity.Article;
import co.fatboa.filesystem.domain.params.ArticleParams;
import co.fatboa.filesystem.service.ArticleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private IArticleDao articleDao;

    @Override
    public Article save(Article entity) {
        return this.articleDao.save(entity);
    }

    @Override
    public void delete(Serializable... ids) {
        try {
            articleDao.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Article> findAll(ArticleParams articleParams) {

        return this.articleDao.findAll(new Query());
    }

    @Override
    public Article findOne(ArticleParams articleParams) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(articleParams.getId());
        Pageable pageable = new PageRequest(1, 1);
        query.with(pageable);
        query.addCriteria(criteria);
        return this.articleDao.findOne(query);
    }

    @Override
    public Article findById(String id) {

        return this.articleDao.findById(id);
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
