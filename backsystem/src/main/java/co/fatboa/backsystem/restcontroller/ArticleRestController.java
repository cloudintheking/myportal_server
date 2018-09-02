package co.fatboa.backsystem.restcontroller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hl
 * @Date: 2018/9/2 13:46
 * @Description: 文章控制器
 * @Modified By:
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api("处理文章相关的请求")
@RestController
@RequestMapping("/japi/backsystem/article")
public class ArticleRestController {
}
