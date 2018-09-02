package co.fatboa.backsystem.restcontroller;

import co.fatboa.core.restcontroller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hl
 * @Date: 2018/9/2 19:04
 * @Description: 配置控制器
 * @Modified By:
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "处理配置相关的请求")
@RestController
@RequestMapping("/japi/backsystem/option")
public class OptionRestController extends BaseController {
}
