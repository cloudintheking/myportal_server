package co.fatboa.backsystem.domain.mapper;

import co.fatboa.backsystem.domain.dto.ArticleDto;
import co.fatboa.backsystem.domain.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/2 12:00
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mappings({
            @Mapping(target = "id", source = "article.id"),
            @Mapping(target = "title", source = "article.title"),
            @Mapping(target = "content", source = "article.content"),
            @Mapping(target = "preview", source = "article.preview"),
            @Mapping(target = "cover", source = "article.cover"),
            @Mapping(target = "date", source = "article.date"),
            @Mapping(target = "show", source = "article.show"),
            @Mapping(target = "category", source = "article.category.id")
    })
    ArticleDto from(Article article);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "title", source = "dto.title"),
            @Mapping(target = "content", source = "dto.content"),
            @Mapping(target = "preview", source = "dto.preview"),
            @Mapping(target = "cover", source = "dto.cover"),
            @Mapping(target = "date", source = "dto.date"),
            @Mapping(target = "show", source = "dto.show"),
            @Mapping(target = "category.id", source = "dto.category")
    })
    Article to(ArticleDto dto);

    List<ArticleDto> fromList(List<Article> articles);

    List<Article> toList(List<ArticleDto> dtos);
}
