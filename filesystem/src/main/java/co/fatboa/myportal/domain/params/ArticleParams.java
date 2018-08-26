
package co.fatboa.myportal.domain.params;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 9:42 2018/8/23
 * @Modified By:
 * @Version 1.0
 */
public class ArticleParams {
    @ApiModelProperty("文章id")
    private String id;
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("文章父级")
    @SerializedName("parent.$id")
    private String pid;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
