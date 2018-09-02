package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.entity.LinkGroup;
import co.fatboa.core.restcontroller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hl
 * @Date: 2018/9/2 17:22
 * @Description: 链接组控制器
 * @Modified By:
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api("处理链接组相关的请求")
@RestController
@RequestMapping("/japi/backsystem/linkgroup")
public class LinkGroupRestController extends BaseController<LinkGroup> {
}
