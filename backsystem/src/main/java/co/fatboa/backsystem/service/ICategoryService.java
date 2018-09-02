package co.fatboa.backsystem.service;

import co.fatboa.backsystem.domain.dto.CategoryDto;
import co.fatboa.backsystem.domain.params.CategoryParam;
import co.fatboa.backsystem.domain.entity.Category;
import co.fatboa.core.Service.IBaseService;

import java.util.List;


/**
 * @Auther: hl
 * @Date: 2018/9/1 07:53
 * @Description: 栏目类服务接口
 * @Modified By:
 * @Version 1.0
 */
public interface ICategoryService extends IBaseService<Category, CategoryDto, CategoryParam, String> {
    /**
     * @param id         栏目id
     * @param showChilds 是否显示子栏目
     * @param deep       深度
     * @param byShow     是否按照栏目状态查询 若true则增加查询show=true 的查询条件
     * @return
     * @throws Exception
     */
    Category findById(String id, Boolean showChilds, Integer deep, Boolean byShow) throws Exception;

    /**
     * 栏目树形结构
     *
     * @param deep   树的深度
     * @param byShow 是否按栏目显示状态查询
     * @return
     */
    List<Category> findByTree(Integer deep, Boolean byShow);

    /**
     * 删除栏目下的子级栏目升级
     *
     * @param id         删除栏目的id
     * @param deleA      是否删除被关联的文章
     * @param deleZ      是否删除被关联的首页展区
     * @param articleref 如果不删除被关联文章,文章关联到指定栏目下
     * @param zoneref    如果不删除首页展区,首页展区关联到指定栏目下
     */
    void delete(String id, Boolean deleA, Boolean deleZ, String articleref, String zoneref) throws Exception;
}
