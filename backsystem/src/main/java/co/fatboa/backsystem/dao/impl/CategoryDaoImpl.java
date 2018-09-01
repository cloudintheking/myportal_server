package co.fatboa.backsystem.dao.impl;

import co.fatboa.backsystem.dao.ICategoryDao;
import co.fatboa.backsystem.domain.entity.Category;
import co.fatboa.core.dao.Impl.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/1 07:34
 * @Description: 栏目类数据访问接口实现类
 * @Modified By:
 * @Version 1.0
 */
@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements ICategoryDao {

    /**
     * 获取泛型参数的类
     *
     * @return
     */
    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

}
