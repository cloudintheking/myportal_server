package co.fatboa.backsystem.domain.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: hl
 * @Date: 2018/9/1 08:26
 * @Description: 其他配置查询参数类
 * @Modified By:
 * @Version 1.0
 */
@Data
public class OptionParam  {
    @ApiModelProperty("配置id")
    private String id;
    @ApiModelProperty("删除顶部图片id")
    private String deleteImg;
    @ApiModelProperty("新增顶部图片id")
    private String addImg;
    @ApiModelProperty("替换顶部图片id")
    private String editImg;
    @ApiModelProperty("公司介绍")
    private String about;
    @ApiModelProperty("公司联系")
    private String contact;
    @ApiModelProperty("网站著作权")
    private String copyright;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeleteImg() {
        return deleteImg;
    }

    public void setDeleteImg(String deleteImg) {
        this.deleteImg = deleteImg;
    }

    public String getAddImg() {
        return addImg;
    }

    public void setAddImg(String addImg) {
        this.addImg = addImg;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getEditImg() {
        return editImg;
    }

    public void setEditImg(String editImg) {
        this.editImg = editImg;
    }
}
