package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.entity.LinkGroup;
import co.fatboa.backsystem.domain.params.LinkGroupParam;
import co.fatboa.backsystem.service.ILinkGroupService;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ILinkGroupService linkGroupService;

    @ApiOperation("新增链接组")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Map> save(@RequestBody(required = true) @ApiParam("链接组属性") LinkGroup group) {
        try {
            this.linkGroupService.save(group);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(group, "新增链接成功"), HttpStatus.OK);
    }

    @ApiOperation("查询链接组")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map> findAll(@RequestBody @ApiParam("链接组查询参数") LinkGroupParam param) {
        return new ResponseEntity<Map>(successResult(this.linkGroupService.findAll(param), "新增链接成功"), HttpStatus.OK);
    }

    @ApiOperation("查询链接组并携带链接信息")
    @RequestMapping(value = "/findAllwithLinks", method = RequestMethod.POST)
    public ResponseEntity<Map> findAllwithLinks(@RequestBody @ApiParam("链接组查询参数") LinkGroupParam param) {
        return new ResponseEntity<Map>(successResult(this.linkGroupService.findAllwithLinks(param), "新增链接成功"), HttpStatus.OK);
    }

    @ApiOperation("删除链接组")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<Map> delete(@RequestParam(required = true) @ApiParam("链接组id") String id) {
        try {
            this.linkGroupService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("不存在id=" + e.getMessage() + "的链接组，删除失败");
            return new ResponseEntity<Map>(errorResult("删除失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "删除成功"), HttpStatus.OK);
    }

}
