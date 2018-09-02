package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.params.OptionParam;
import co.fatboa.backsystem.service.IOptionService;
import co.fatboa.core.restcontroller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IOptionService optionService;

    @ApiOperation("获取配置")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<Map> find() {
        return new ResponseEntity<Map>(successResult(this.optionService.getOption(), "获取成功"), HttpStatus.OK);
    }

    @ApiOperation("更新配置")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Map> update(@RequestBody(required = true) OptionParam param) {
        try {
            this.optionService.update(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("更新配置失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "更新配置成功"), HttpStatus.OK);
    }
}
