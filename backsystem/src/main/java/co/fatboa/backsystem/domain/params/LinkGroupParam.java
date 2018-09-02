package co.fatboa.backsystem.domain.params;

import co.fatboa.core.domain.queryparams.BaseQueryParam;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: hl
 * @Date: 2018/9/1 08:25
 * @Description: 链接组查询参数类
 * @Modified By:
 * @Version 1.0
 */
public class LinkGroupParam extends BaseQueryParam {
    @ApiModelProperty("链接组id")
    private String id;
    @ApiModelProperty("链接组名")
    private String name;

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
}
