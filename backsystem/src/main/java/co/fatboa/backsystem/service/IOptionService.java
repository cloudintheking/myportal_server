package co.fatboa.backsystem.service;

import co.fatboa.backsystem.domain.entity.Option;
import co.fatboa.backsystem.domain.params.OptionParam;
import co.fatboa.core.Service.IBaseService;

/**
 * @Auther: hl
 * @Date: 2018/9/2 17:59
 * @Description: 其他配置服务接口
 * @Modified By:
 * @Version 1.0
 */
public interface IOptionService {
    /**
     * 获取配置
     * @return
     */
    Option getOption();

    /**
     * 更新配置
     * @param param
     */
    void update(OptionParam param) throws Exception;
}
