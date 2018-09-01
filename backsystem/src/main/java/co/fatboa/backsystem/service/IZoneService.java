package co.fatboa.backsystem.service;

import co.fatboa.backsystem.domain.dto.CategoryDto;
import co.fatboa.backsystem.domain.dto.ZoneDto;
import co.fatboa.backsystem.domain.entity.Category;
import co.fatboa.backsystem.domain.entity.Zone;
import co.fatboa.backsystem.domain.params.CategoryParam;
import co.fatboa.backsystem.domain.params.ZoneParam;
import co.fatboa.core.Service.IBaseService;
import org.springframework.data.domain.Page;

/**
 * @Auther: hl
 * @Date: 2018/9/1 18:39
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
public interface IZoneService extends IBaseService<Zone, ZoneDto, ZoneParam, String> {

}
