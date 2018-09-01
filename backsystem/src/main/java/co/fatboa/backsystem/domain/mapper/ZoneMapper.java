package co.fatboa.backsystem.domain.mapper;

import co.fatboa.backsystem.domain.dto.ZoneDto;
import co.fatboa.backsystem.domain.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/1 18:47
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ZoneMapper {
    @Mappings({
            @Mapping(target = "id", source = "zone.id"),
            @Mapping(target = "name", source = "zone.name"),
            @Mapping(target = "style", source = "zone.style"),
            @Mapping(target = "show", source = "zone.show"),
            @Mapping(target = "width", source = "zone.width"),
            @Mapping(target = "pos", source = "zone.pos"),
            @Mapping(target = "category", source = "zone.category.id")

    })
    public ZoneDto from(Zone zone);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "style", source = "dto.style"),
            @Mapping(target = "show", source = "dto.show"),
            @Mapping(target = "width", source = "dto.width"),
            @Mapping(target = "pos", source = "dto.pos"),
            @Mapping(target = "category.id", source = "dto.category")

    })
    public Zone to(ZoneDto dto);

    List<ZoneDto> fromLsit(List<Zone> zones);

    List<Zone> toList(List<ZoneDto> dtos);
}
