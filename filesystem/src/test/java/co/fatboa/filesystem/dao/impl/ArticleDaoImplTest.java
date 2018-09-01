package co.fatboa.filesystem.dao.impl;

import co.fatboa.filesystem.domain.dto.ArticleDto;
import co.fatboa.filesystem.domain.entity.Article;
import co.fatboa.filesystem.domain.mapper.ArticleMapper;
import co.fatboa.filesystem.domain.params.ArticleParams;
import co.fatboa.filesystem.service.ArticleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleDaoImplTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void findAll() {
        for (Article a : articleService.findAll(null)) {
            System.out.println(a.toString());
        }
    }

    @Test
    public void findOne() {
        ArticleParams params = new ArticleParams();
        // params.setId("1");
        Criteria criteria = new Criteria();
        criteria.and("id").is("5b8a04fc7181b927f47b303a");
        Article article = this.mongoTemplate.findOne(new Query().addCriteria(criteria), Article.class);
        System.out.println(article.toString());
//        ArticleDto articleDto = articleMapper.form(article);
//        ArticleDto articleDto=new ArticleDto();
//        articleDto.setId("dasfdfsdfsff");
//        articleDto.setParentId("");
//        System.out.println(articleDto.toString());
//        System.out.println(articleMapper.to(articleDto));
//        this.gridFsTemplate.delete(Query.query(Criteria.where("use").is(true)));
    }

    @Test
    public void save() {
        ArticleParams params = new ArticleParams();
        params.setId("5b8a26f17181b91aa04e0e10");
        Article articlep = this.articleService.findOne(params);
        Article article = this.articleService.findById("5b8a04fc7181b927f47b303a");
        //article.setDescription("ai");
        article.setLikes(7);
        article.setTitle("cccc");
        article.setDate(new Date());
        article.setParent(articlep);
        articleService.save(article);
//        co.fatboa.filesystem.domain.entity.Test t = new co.fatboa.filesystem.domain.entity.Test();
//        t.setTestAge("32");
//        t.setTestname("sfs");
//        t.setDate(new Date());
        // this.mongoTemplate.insert(t);
//        co.fatboa.filesystem.domain.entity.Test tt = this.mongoTemplate.findOne(Query.query(new Criteria().and("testAge").is("31")), co.fatboa.filesystem.domain.entity.Test.class);
//        System.out.println(tt == null ? "空" : "不空");
    }

    @Test
    public void delete() {
        String[] s = new String[]{};
        List<String> ids = Arrays.asList(s);


        articleService.delete(ids.toArray());
    }

    @Test
    public void updateA() {
        ArticleParams params = new ArticleParams();
       params.setId("5b8a044e7181b900cce69050");
      //  params.setPid("5b8a044e7181b900cce69050");
        Article articler = this.articleService.findOne(params);
        this.mongoTemplate.upsert(new Query(Criteria.where("title").is("update8")), new Update().set("parent.$id", "5b8a044e7181b900cce69050").set("parent.$ref","article"), Article.class);
        //   articleService.update(params);
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