package co.fatboa.myportal.dao.impl;

import co.fatboa.myportal.dao.IArticleDao;
import co.fatboa.myportal.domain.entity.Article;
import org.springframework.stereotype.Repository;


/**
 * @Author: hl
 * @Description: TODO
 * @Date: 17:25 2018/8/22
 * @Modified By:
 * @Version 1.0
 */
@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements IArticleDao {
    @Override
    protected Class<Article> getEnityClass() {
        return Article.class;
    }
}
