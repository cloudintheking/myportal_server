package co.fatboa.backsystem.service;

import co.fatboa.backsystem.domain.entity.User;
import co.fatboa.backsystem.domain.params.UserParam;
import co.fatboa.core.Service.IBaseService;

/**
 * @Auther: hl
 * @Date: 2018/9/6 10:06
 * @Description: 用户服务接口
 * @Modified By:
 * @Version 1.0
 */
public interface IUserService extends IBaseService<User, User, UserParam, String> {
    /**
     * 登录
     * @param param
     * @return
     */
    User login(UserParam param) throws Exception;
}
