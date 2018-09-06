package co.fatboa.backsystem.dao.impl;

import co.fatboa.backsystem.dao.IZoneDao;
import co.fatboa.backsystem.domain.entity.Zone;
import co.fatboa.core.dao.Impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @Auther: hl
 * @Date: 2018/9/1 07:39
 * @Description: 首页展区类数据访问接口实现
 * @Modified By:
 * @Version 1.0
 */
@Repository
public class ZoneDaoImpl extends BaseDaoImpl<Zone> implements IZoneDao {
    /**
     * 获取泛型参数的类
     *
     * @return
     */
    @Override
    protected Class<Zone> getEntityClass() {
        return Zone.class;
    }
}
