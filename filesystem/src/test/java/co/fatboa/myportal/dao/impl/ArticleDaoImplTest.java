package co.fatboa.myportal.dao.impl;

import co.fatboa.myportal.domain.entity.Article;
import co.fatboa.myportal.domain.params.ArticleParams;
import co.fatboa.myportal.service.ArticleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleDaoImplTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void findAll() {
        for (Article a : articleService.findAll(null)) {
            System.out.println(a.toString());
        }
    }

    @Test
    public void findOne() {
//        ArticleParams params = new ArticleParams();
//        params.setId("5b80b494eb60270f7c80a136");
//        Article article = this.articleService.findOne(params);
//        List<Article> articles = new ArrayList<>();
//        Article c = article.getChilds().get(0);
//        System.out.println(c.toString());

        this.gridFsTemplate.delete(Query.query(Criteria.where("use").is(true)));
    }

    @Test
    public void save() {
        ArticleParams params = new ArticleParams();
        params.setId("5b80b494eb60270f7c80a13a");
        Article article = this.articleService.findOne(params);
        article.setTitle(null);
//        List<Article> articles = article.getChilds();
//        Article article1 = articles.get(0);
//        article1.setTitle("2e324234");
//        article.setChilds(articles);
        articleService.save(article);
    }

    @Test
    public void delete() {
        List<String> ids = new ArrayList<>();
        articleService.delete(ids.toArray());
    }

    @Test
    public void updateA() {
        ArticleParams params = new ArticleParams();
        params.setId("5b80c183eb60270c2c8c941b");
        params.setPid("5b80bafeeb602720f446ffba");
        params.setTitle("update8");
        articleService.update(params);
    }

    @Test
    public void updateFile() {
        // DBObject dbObject = new BasicDBObject().append("_id", "3626234d2b06ddc4a3bd92ddbddb5cfc");
//        GridFSDBFile gridFSDBFile = this.gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is("5b821c71eb60271824654046")));
//        System.out.println(gridFSDBFile.toString());
        Update update = new Update();
        update.set("use", true);
        this.mongoTemplate.upsert(Query.query(Criteria.where("_id").is("5b821c71eb60271824654046")), update, "fs.files");


    }

    @Test
    public void gsonTest() {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "gg");
        map.put("test", null);
        String json = gson.toJson(map);
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> testMap = gson.fromJson(json, type);
        for (String key : testMap.keySet()) {
            System.out.println("key:" + key + " value:" + testMap.get(key).toString());
        }
    }

}