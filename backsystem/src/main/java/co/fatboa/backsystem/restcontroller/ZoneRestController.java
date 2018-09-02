package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.dto.ZoneDto;
import co.fatboa.backsystem.domain.entity.Zone;
import co.fatboa.backsystem.domain.params.ZoneParam;
import co.fatboa.backsystem.service.IZoneService;
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

import javax.crypto.MacSpi;
import java.util.List;
import java.util.Map;

/**
 * @Auther: hl
 * @Date: 2018/9/2 09:48
 * @Description: 首页展区控制器
 * @Modified By:
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "处理首页展区相关的请求")
@RestController
@RequestMapping("/japi/backsystem/zone")
public class ZoneRestController extends BaseController<Zone> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IZoneService zoneService;

    @ApiOperation("新增首页展区")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Map> save(@RequestBody(required = true) @ApiParam("首页展区属性") ZoneDto zoneDto) {
        try {
            this.zoneService.save(zoneDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("新增失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(zoneDto, "新增成功"), HttpStatus.OK);
    }

    @ApiOperation("查询全部")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map> findAll(@RequestBody @ApiParam("首页展区查询参数") ZoneParam param) {
        List<Zone> zones = this.zoneService.findAll(param);
        return new ResponseEntity<Map>(successResult(zones, "查询成功"), HttpStatus.OK);
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public ResponseEntity<Map> findByPage(@RequestBody @ApiParam("首页展区查询参数") ZoneParam param) {
        Page<Zone> zones = this.zoneService.findByPage(param);
        return new ResponseEntity<Map>(pageResult(zones), HttpStatus.OK);
    }

    @ApiOperation("根据id查询")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<Map> findById(@RequestParam(required = true) @ApiParam("首页展区查询参数id") String id) {
        Zone zone = null;
        try {
            zone = this.zoneService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("查询失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(zone, "查询成功"), HttpStatus.OK);
    }

    @ApiOperation("更新首页展区")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Map> update(@RequestBody @ApiParam("首页展区更新参数") ZoneDto zoneDto) {
        try {
            this.zoneService.update(zoneDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("更新失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "查询成功"), HttpStatus.OK);
    }

    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<Map> delete(@RequestParam String... ids) {
        try {
            this.zoneService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("该id:" + e.getMessage() + "不存在,停止删除");
            return new ResponseEntity<Map>(errorResult("删除失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "删除成功"), HttpStatus.OK);
    }
}
