package co.fatboa.filesystem.dao.impl;

import co.fatboa.core.dao.Impl.BaseDaoImpl;
import co.fatboa.filesystem.dao.IArticleDao;
import co.fatboa.filesystem.domain.entity.Article;
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

    /**
     * 获取泛型参数的类
     *
     * @return
     */
    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }
}
