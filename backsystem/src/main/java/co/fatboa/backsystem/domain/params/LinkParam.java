package co.fatboa.backsystem.domain.params;

import co.fatboa.core.domain.queryparams.BaseQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: hl
 * @Date: 2018/9/1 08:26
 * @Description: 链接查询参数类
 * @Modified By:
 * @Version 1.0
 */
@Data
public class LinkParam extends BaseQueryParam {
    @ApiModelProperty("链接id")
    private String id;
    @ApiModelProperty("链接名")
    private String name;
    @ApiModelProperty("关联链接组id")
    private String group;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
