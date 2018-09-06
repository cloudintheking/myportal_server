package co.fatboa.backsystem.domain.mapper;

import co.fatboa.backsystem.domain.dto.UserDto;
import co.fatboa.backsystem.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Auther: hl
 * @Date: 2018/9/6 10:55
 * @Description: user转换
 * @Modified By:
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "name", source = "user.name"),
            @Mapping(target = "sex", source = "user.sex"),
            @Mapping(target = "age", source = "user.age"),
            @Mapping(target = "department", source = "user.department"),
            @Mapping(target = "role", source = "user.role"),
    })
    UserDto from(User user);

    @Mappings({
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "sex", source = "dto.sex"),
            @Mapping(target = "age", source = "dto.age"),
            @Mapping(target = "department", source = "dto.department"),
            @Mapping(target = "role", source = "dto.role"),
    })
    User to(UserDto dto);
}
