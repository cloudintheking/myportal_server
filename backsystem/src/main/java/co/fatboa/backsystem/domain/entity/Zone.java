package co.fatboa.backsystem.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: hl
 * @Description: 首页展区类
 * @Date: 21:19 2018/8/27
 * @Modified By:
 * @Version 1.0
 */
@Data
@Document
public class Zone {
    @Id
    private String id; //展区id
    private String name; //展区名
    private String style; //展区样式
    private String show; //是否显示 0：不显示 1：显示
    private String width; //展区宽度比
    private String pos; //展区位置
    @DBRef
    private Category category; //关联栏目

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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
