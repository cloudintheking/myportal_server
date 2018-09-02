package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.dto.ArticleDto;
import co.fatboa.backsystem.domain.entity.Article;
import co.fatboa.backsystem.domain.params.ArticleParam;
import co.fatboa.backsystem.service.IArticleService;
import co.fatboa.core.restcontroller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
public class ArticleRestController extends BaseController<Article> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IArticleService articleService;

    @ApiOperation("新增文章")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Map> save(@RequestBody(required = true) @ApiParam("文章属性") ArticleDto dto) {
        try {
            dto = this.articleService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("新增失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(dto, "新增成功"), HttpStatus.OK);
    }

    @ApiOperation("查询全部")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map> findAll(@RequestBody @ApiParam("文章查询参数") ArticleParam param) {
        List<Article> articles = this.articleService.findAll(param);
        return new ResponseEntity<Map>(successResult(articles, "查询成功"), HttpStatus.OK);
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public ResponseEntity<Map> findByPage(@RequestBody @ApiParam("首页展区查询参数") ArticleParam param) {
        Page<Article> articles = this.articleService.findByPage(param);
        return new ResponseEntity<Map>(pageResult(articles), HttpStatus.OK);
    }

    @ApiOperation("根据id查询")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<Map> findById(@RequestParam(required = true) @ApiParam("文章id") String id) {
        Article article = null;
        try {
            article = this.articleService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("查询失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(article, "查询成功"), HttpStatus.OK);
    }

    @ApiOperation("更新首页展区")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Map> update(@RequestBody @ApiParam("文章更新参数") ArticleDto articleDto) {
        try {
            this.articleService.update(articleDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("更新失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(articleDto, "更新成功"), HttpStatus.OK);
    }

    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<Map> delete(@RequestParam String... ids) {
        try {
            this.articleService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("该id:" + e.getMessage() + "不存在,停止删除");
            return new ResponseEntity<Map>(errorResult("删除失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "删除成功"), HttpStatus.OK);
    }
}
