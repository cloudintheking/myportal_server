package co.fatboa.backsystem.service;

import co.fatboa.backsystem.domain.dto.ArticleDto;
import co.fatboa.backsystem.domain.entity.Article;
import co.fatboa.backsystem.domain.params.ArticleParam;
import co.fatboa.core.Service.IBaseService;

/**
 * @Auther: hl
 * @Date: 2018/9/1 07:50
 * @Description: 文章类服务接口
 * @Modified By:
 * @Version 1.0
 */
public interface IArticleService extends IBaseService<Article, ArticleDto, ArticleParam, String> {

}
