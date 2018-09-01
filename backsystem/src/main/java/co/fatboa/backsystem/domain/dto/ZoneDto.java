package co.fatboa.backsystem.domain.dto;

import lombok.Data;

/**
 * @Auther: hl
 * @Date: 2018/9/1 18:42
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Data
public class ZoneDto {
    private String id; //展区id
    private String name; //展区名
    private String style; //展区样式
    private String show; //是否显示 0：不显示 1：显示
    private String width; //展区宽度比
    private String pos; //展区位置
    private String category; //关联栏目id

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
