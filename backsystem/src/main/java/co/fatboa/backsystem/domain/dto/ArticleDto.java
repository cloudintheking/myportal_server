package co.fatboa.backsystem.domain.dto;


import lombok.Data;

import java.util.Date;

/**
 * @Auther: hl
 * @Date: 2018/9/2 11:28
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Data
public class ArticleDto {
    private String id; //文章id
    private String title; //文章标题
    private String content; //文章内容
    private String preview; //预览内容
    private String cover; //文章封面
    private Date date; //发布时间
    private Boolean show; //是否显示
    private String category; // 关联栏目

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
