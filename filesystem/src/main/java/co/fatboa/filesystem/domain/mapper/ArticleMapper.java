package co.fatboa.filesystem.domain.mapper;

import co.fatboa.filesystem.domain.dto.ArticleDto;
import co.fatboa.filesystem.domain.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Auther: hl
 * @Date: 2018/9/1 10:54
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mappings({
            @Mapping(target = "id", source = "article.id"),
            @Mapping(target = "parentId", source = "article.parent.id")
    })
    public ArticleDto form(Article article);

    @Mappings({
            @Mapping(target = "parent.id", source = "dto.parentId"),
            @Mapping(target = "id", source = "dto.id")
    })
    Article to(ArticleDto dto);
}
