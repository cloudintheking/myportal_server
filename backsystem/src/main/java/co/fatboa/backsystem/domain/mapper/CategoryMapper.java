package co.fatboa.backsystem.domain.mapper;

import co.fatboa.backsystem.domain.dto.CategoryDto;
import co.fatboa.backsystem.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/1 11:37
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    /**
     * pojo转dto
     *
     * @param category
     * @return
     */
    @Mappings({
            @Mapping(target = "id", source = "category.id"),
            @Mapping(target = "name", source = "category.name"),
            @Mapping(target = "level", source = "category.level"),
            @Mapping(target = "style", source = "category.style"),
            @Mapping(target = "route", source = "category.route"),
            @Mapping(target = "show", source = "category.show"),
            @Mapping(target = "parent", source = "category.parent.id"),

    })
    CategoryDto from(Category category);

    /**
     * dto转pojo
     *
     * @param dto
     * @return
     */
    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "level", source = "dto.level"),
            @Mapping(target = "style", source = "dto.style"),
            @Mapping(target = "route", source = "dto.route"),
            @Mapping(target = "show", source = "dto.show"),
            @Mapping(target = "parent.id", source = "dto.parent"),

    })
    Category to(CategoryDto dto);

    /**
     * 批量pojo转dto
     *
     * @param categories
     * @return
     */
    List<CategoryDto> fromList(List<Category> categories);

    /**
     * 批量dto转pojo
     * @param categoryDtos
     * @return
     */
    List<Category> toList(List<CategoryDto> categoryDtos);
}
