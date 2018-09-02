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
    private Boolean show; //是否显示 0：不显示 1：显示
    private Integer width; //展区宽度比
    private Integer pos; //展区位置
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

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
