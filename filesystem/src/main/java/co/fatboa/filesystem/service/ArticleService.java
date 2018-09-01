package co.fatboa.filesystem.service;

import co.fatboa.filesystem.domain.entity.Article;
import co.fatboa.filesystem.domain.params.ArticleParams;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 9:33 2018/8/23
 * @Modified By:
 * @Version 1.0
 */
public interface ArticleService {
    Article save(Article entity);
    void delete(Serializable...  id);
    List<Article> findAll(ArticleParams articleParams);

    Article findOne(ArticleParams articleParams);

    Article findById(String id);

    void update(ArticleParams params);
}
