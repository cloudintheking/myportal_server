package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.dto.LinkDto;
import co.fatboa.backsystem.domain.entity.Link;
import co.fatboa.backsystem.domain.params.LinkParam;
import co.fatboa.backsystem.service.ILinkService;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ILinkService linkService;

    @ApiOperation("新增链接")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Map> save(@RequestBody(required = true) @ApiParam("链接参数") LinkDto dto) {
        try {
            dto = this.linkService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("新增失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(dto, "新增成功"), HttpStatus.OK);
    }

    @ApiOperation("查询全部")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map> findAll(@RequestBody @ApiParam("链接查询参数") LinkParam param) {
        List<Link> links = this.linkService.findAll(param);
        return new ResponseEntity<Map>(successResult(links, "查询成功"), HttpStatus.OK);
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public ResponseEntity<Map> findByPage(@RequestBody @ApiParam("链接查询参数") LinkParam param) {
        Page<Link> links = this.linkService.findByPage(param);
        return new ResponseEntity<Map>(pageResult(links), HttpStatus.OK);
    }
    @ApiOperation("根据id查询")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<Map> findById(@RequestParam(required = true) @ApiParam("链接id") String id) {
        Link link = null;
        try {
            link = this.linkService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("查询失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(link, "查询成功"), HttpStatus.OK);
    }
    @ApiOperation("更新首页展区")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Map> update(@RequestBody @ApiParam("链接更新参数") LinkDto dto) {
        try {
            this.linkService.update(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("更新失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(dto, "更新成功"), HttpStatus.OK);
    }
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<Map> delete(@RequestParam("链接可变id") String... ids) {
        try {
            this.linkService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("该id:" + e.getMessage() + "不存在,停止删除");
            return new ResponseEntity<Map>(errorResult("删除失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "删除成功"), HttpStatus.OK);
    }
}
