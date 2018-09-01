package co.fatboa.backsystem.domain.params;

import co.fatboa.core.domain.queryparams.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Sort;

/**
 * @Auther: hl
 * @Date: 2018/9/1 08:00
 * @Description: 栏目查询参数
 * @Modified By:
 * @Version 1.0
 */
@ApiModel("栏目查询参数类")
public class CategoryParam extends BaseQueryParam {
    @ApiModelProperty("栏目id")
    private String id;
    @ApiModelProperty("栏目名称")
    private String name;
    @ApiModelProperty("栏目等级")
    private String level;
    @ApiModelProperty("显示状态")
    private String show;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
}
