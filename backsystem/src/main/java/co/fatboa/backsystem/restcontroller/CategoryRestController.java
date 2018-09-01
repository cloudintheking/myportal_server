package co.fatboa.backsystem.restcontroller;

import co.fatboa.backsystem.domain.dto.CategoryDto;
import co.fatboa.backsystem.domain.entity.Category;
import co.fatboa.backsystem.service.ICategoryService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: hl
 * @Date: 2018/9/1 14:50
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/japi/backsystem/category")
@Api(value = "backsystem", description = "处理栏目类相关请求")
public class CategoryRestController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("根据id查询")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<Map> findById(@RequestParam(required = true) @ApiParam("栏目id") String id,
                                        @RequestParam(required = false, defaultValue = "false") @ApiParam("是否显示子级栏目") Boolean showChilds,
                                        @RequestParam(required = false, defaultValue = "100") @ApiParam("栏目深度") Integer deep) {
        Category category = null;
        try {
            category = this.categoryService.findById(id, showChilds, deep);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("查找失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(category, "查找成功"), HttpStatus.OK);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation("新增栏目")
    public ResponseEntity<Map> save(@RequestBody(required = true) @ApiParam("栏目属性") CategoryDto categoryDto) {
        CategoryDto dto = null;
        try {
            dto = this.categoryService.save(categoryDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("新增失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(dto, "新增成功"), HttpStatus.OK);
    }

    @ApiOperation("更新栏目")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Map> update(@RequestBody(required = true) @ApiParam("栏目属性") CategoryDto categoryDto) {
        try {
            this.categoryService.update(categoryDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult("更新失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "更新成功"), HttpStatus.OK);
    }

    @ApiOperation("删除栏目")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<Map> delete(@RequestParam String... ids) {
        try {
            this.categoryService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("该id:" + e.getMessage() + "不存在,停止删除");
            return new ResponseEntity<Map>(errorResult("删除失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "删除成功"), HttpStatus.OK);
    }
}
