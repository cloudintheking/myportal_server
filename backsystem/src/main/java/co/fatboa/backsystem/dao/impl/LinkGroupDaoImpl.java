package co.fatboa.backsystem.dao.impl;

import co.fatboa.backsystem.dao.ILinkGroupDao;
import co.fatboa.backsystem.domain.entity.LinkGroup;
import co.fatboa.core.dao.Impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @Auther: hl
 * @Date: 2018/9/1 07:43
 * @Description: 链接组类数据访问接口实现类
 * @Modified By:
 * @Version 1.0
 */
@Repository
public class LinkGroupDaoImpl extends BaseDaoImpl<LinkGroup> implements ILinkGroupDao {
    /**
     * 获取泛型参数的类
     *
     * @return
     */
    @Override
    protected Class<LinkGroup> getEntityClass() {
        return LinkGroup.class;
    }
}
