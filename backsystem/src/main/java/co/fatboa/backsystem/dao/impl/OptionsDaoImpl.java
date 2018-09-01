package co.fatboa.backsystem.dao.impl;

import co.fatboa.backsystem.dao.IOptionsDao;
import co.fatboa.backsystem.domain.entity.Options;
import co.fatboa.core.dao.Impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @Auther: hl
 * @Date: 2018/9/1 07:47
 * @Description: 其他配置类数据访问接口实现类
 * @Modified By:
 * @Version 1.0
 */
@Repository
public class OptionsDaoImpl extends BaseDaoImpl<Options> implements IOptionsDao {
    /**
     * 获取泛型参数的类
     *
     * @return
     */
    @Override
    protected Class<Options> getEntityClass() {
        return Options.class;
    }
}
