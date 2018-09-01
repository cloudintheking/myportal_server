package co.fatboa.backsystem.dao.impl;

import co.fatboa.backsystem.dao.IArticleDao;
import co.fatboa.backsystem.domain.entity.Article;
import co.fatboa.core.dao.Impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @Auther: hl
 * @Date: 2018/9/1 07:45
 * @Description: 文章类数据访问接口实现类
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
