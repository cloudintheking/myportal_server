package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.entity.Link;
import co.fatboa.core.restcontroller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hl
 * @Date: 2018/9/2 17:24
 * @Description: 链接控制器
 * @Modified By:
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "处理链接相关的请求")
@RestController
@RequestMapping("/japi/backsystem/link")
public class LinkRestController extends BaseController<Link> {
}
