package co.fatboa.backsystem.dao.impl;

import co.fatboa.backsystem.dao.ILinkDao;
import co.fatboa.backsystem.domain.entity.Link;
import co.fatboa.core.dao.Impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @Auther: hl
 * @Date: 2018/9/1 07:41
 * @Description: 链接类数据访问接口实现类
 * @Modified By:
 * @Version 1.0
 */
@Repository
public class LinkDaoImpl extends BaseDaoImpl<Link> implements ILinkDao  {
    /**
     * 获取泛型参数的类
     *
     * @return
     */
    @Override
    protected Class<Link> getEntityClass() {
        return Link.class;
    }
}
