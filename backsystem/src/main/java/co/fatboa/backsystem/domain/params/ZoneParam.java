package co.fatboa.backsystem.domain.params;

import co.fatboa.core.domain.queryparams.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: hl
 * @Date: 2018/9/1 08:25
 * @Description: 首页展示查询参数类
 * @Modified By:
 * @Version 1.0
 */
@ApiModel("首页展示查询参数类")
public class ZoneParam extends BaseQueryParam {
    @ApiModelProperty("展区id")
    private String id;
    @ApiModelProperty("展区名")
    private String name;
    @ApiModelProperty("展区样式")
    private String style;
    @ApiModelProperty("显示状态")
    private String show;
    @ApiModelProperty("关联栏目id")
    private String category;

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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
