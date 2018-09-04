package co.fatboa.backsystem.service;

import co.fatboa.backsystem.domain.entity.LinkGroup;
import co.fatboa.backsystem.domain.params.LinkGroupParam;
import co.fatboa.core.Service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/2 16:34
 * @Description:
 * @Modified By:
 * @Version 1.0
 */

public interface ILinkGroupService extends IBaseService<LinkGroup, LinkGroup, LinkGroupParam, String> {
    /**
     * 返回链接组集合并携带链接信息
     *
     * @param params
     * @return
     */
    List<LinkGroup> findAllwithLinks(LinkGroupParam params);
}
