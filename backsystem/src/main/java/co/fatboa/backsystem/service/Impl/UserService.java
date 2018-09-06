package co.fatboa.backsystem.service.Impl;

import co.fatboa.backsystem.domain.entity.User;
import co.fatboa.backsystem.domain.params.UserParam;
import co.fatboa.backsystem.rabbitmq.IQueueMessageService;
import co.fatboa.backsystem.repository.UserRepository;
import co.fatboa.backsystem.service.IUserService;
import co.fatboa.core.utils.MD5Util;
import co.fatboa.rabbitmq.common.enums.ExchangeEnum;
import co.fatboa.rabbitmq.common.enums.QueueEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: hl
 * @Date: 2018/9/6 10:12
 * @Description: 用户服务实现类
 * @Modified By:
 * @Version 1.0
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IQueueMessageService queueMessageService;

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @Override
    public User save(User user) throws Exception {
        user.setId(null);//防止前端误传id
        user.setPassword(MD5Util.encode(user.getPassword()));//加盐
        this.userRepository.save(user);
        queueMessageService.send(user.getId(), ExchangeEnum.USER_REGISTER, QueueEnum.USER_REGISTER);
        return user;
    }

    /**
     * 单个查询
     *
     * @param params
     * @return
     */
    @Override
    public User findOne(UserParam params) {
        return null;
    }

    /**
     * 根据ID查询
     *
     * @param s
     * @return
     */
    @Override
    public User findById(String s) throws Exception {
        return null;
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<User> findByPage(UserParam params) {
        return null;
    }

    /**
     * 参数查询
     *
     * @param params
     * @return
     */
    @Override
    public List<User> findAll(UserParam params) {
        return null;
    }

    /**
     * 批量删除
     *
     * @param strings
     */
    @Override
    public void delete(String... strings) throws Exception {

    }

    /**
     * 单删
     *
     * @param s
     */
    @Override
    public void delete(String s) throws Exception {

    }

    /**
     * 更新
     *
     * @param dto
     */
    @Override
    public void update(User dto) throws Exception {

    }


    /**
     * 登录
     *
     * @param param
     * @return
     */
    @Override
    public User login(UserParam param) throws Exception {
        if (param.getName() == null || param.getPassword() == null) {
            throw new Exception("用户名或密码不能为空");
        }
        User user = this.userRepository.getUser(param.getName(), MD5Util.encode(param.getPassword()));
        if (user == null) {
            throw new Exception("用户名或密码错误");
        }
        return user;
    }
}
