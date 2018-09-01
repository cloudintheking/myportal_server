package co.fatboa.backsystem.service;

import co.fatboa.backsystem.domain.dto.CategoryDto;
import co.fatboa.backsystem.domain.params.CategoryParam;
import co.fatboa.backsystem.domain.entity.Category;
import co.fatboa.core.Service.IBaseService;


/**
 * @Auther: hl
 * @Date: 2018/9/1 07:53
 * @Description: 栏目类服务接口
 * @Modified By:
 * @Version 1.0
 */
public interface ICategoryService extends IBaseService<Category, CategoryDto, CategoryParam, String> {
    Category findById(String id, Boolean showChilds, Integer deep) throws Exception;

}
