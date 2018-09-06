package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.entity.User;
import co.fatboa.backsystem.domain.mapper.UserMapper;
import co.fatboa.backsystem.domain.params.UserParam;
import co.fatboa.backsystem.service.IUserService;
import co.fatboa.core.restcontroller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: hl
 * @Date: 2018/9/6 10:20
 * @Description: 用户控制器
 * @Modified By:
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api("处理用户相关的请求")
@RestController
@RequestMapping("/japi/backsystem/user")
public class UserRestController extends BaseController<User> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map> login(@RequestBody(required = true) @ApiParam("登录信息") UserParam param) {
        User user = null;
        try {
            user = this.userService.login(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult(e.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<Map>(successResult(this.userMapper.from(user), "登录成功"), HttpStatus.OK);
    }

    @ApiOperation("新增用户")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Map> save(@RequestBody(required = true) @ApiParam("用户属性") User user) {
        try {
            this.userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("新增用户失败"), HttpStatus.OK);
        }
        return new ResponseEntity<Map>(successResult(this.userMapper.from(user), "新增用户成功"), HttpStatus.OK);
    }
}
