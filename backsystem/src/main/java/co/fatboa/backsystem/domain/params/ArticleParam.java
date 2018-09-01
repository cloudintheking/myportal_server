package co.fatboa.backsystem.domain.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Auther: hl
 * @Date: 2018/9/1 08:25
 * @Description: 文章查询参数类
 * @Modified By:
 * @Version 1.0
 */
@ApiModel("文章查询参数类")
public class ArticleParam {
    @ApiModelProperty("文章ID")
    private String id;
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("起始日期")
    private Date startDate;
    @ApiModelProperty("截止日期")
    private Date endDate;
    @ApiModelProperty("显示状态")
    private String show;
    @ApiModelProperty("关联栏目")
    private String refCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getRefCategory() {
        return refCategory;
    }

    public void setRefCategory(String refCategory) {
        this.refCategory = refCategory;
    }
}
