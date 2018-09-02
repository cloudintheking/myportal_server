package co.fatboa.backsystem.domain.mapper;

import co.fatboa.backsystem.domain.dto.LinkDto;
import co.fatboa.backsystem.domain.entity.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/2 17:15
 * @Description: 链接转换器
 * @Modified By:
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface LinkMapper {
    @Mappings({
            @Mapping(target = "id", source = "link.id"),
            @Mapping(target = "name", source = "link.name"),
            @Mapping(target = "url", source = "link.url"),
            @Mapping(target = "group", source = "link.group.id"),
    })
    LinkDto from(Link link);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "url", source = "dto.url"),
            @Mapping(target = "group.id", source = "dto.group"),
    })
    Link to(LinkDto dto);

    List<LinkDto> fromList(List<Link> link);

    List<Link> toList(List<LinkDto> dto);
}
