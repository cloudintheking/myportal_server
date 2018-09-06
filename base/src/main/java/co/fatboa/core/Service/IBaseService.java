package co.fatboa.core.Service;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/1 18:55
 * @Description: 服务泛型接口
 * @Modified By:
 * @Version 1.0
 */

/**
 * @param <T>  实体类型
 * @param <D>  DTO
 * @param <P>  参数类型
 * @param <ID> id类型
 */
public interface IBaseService<T, D, P, ID> {
    /**
     * 新增
     *
     * @param dto
     * @return
     */
    D save(D dto) throws Exception;

    /**
     * 单个查询
     *
     * @param params
     * @return
     */
    T findOne(P params);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T findById(ID id) throws Exception;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page<T> findByPage(P params);

    /**
     * 参数查询
     *
     * @param params
     * @return
     */
    List<T> findAll(P params);

    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(ID... ids) throws Exception;

    /**
     * 单删
     *
     * @param id
     */
    void delete(ID id) throws Exception;

    /**
     * 更新
     *
     * @param dto
     */
    void update(D dto) throws Exception;

}
