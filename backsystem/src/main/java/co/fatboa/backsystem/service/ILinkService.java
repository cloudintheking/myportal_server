package co.fatboa.backsystem.service;

import co.fatboa.backsystem.domain.dto.LinkDto;
import co.fatboa.backsystem.domain.entity.Link;
import co.fatboa.backsystem.domain.params.LinkParam;
import co.fatboa.core.Service.IBaseService;

/**
 * @Auther: hl
 * @Date: 2018/9/2 17:11
 * @Description: 链接服务接口
 * @Modified By:
 * @Version 1.0
 */
public interface ILinkService extends IBaseService<Link, LinkDto, LinkParam, String> {
}
