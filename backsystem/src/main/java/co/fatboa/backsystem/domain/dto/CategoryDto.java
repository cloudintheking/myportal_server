package co.fatboa.backsystem.domain.dto;

import lombok.Data;

/**
 * @Auther: hl
 * @Date: 2018/9/1 08:27
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Data
public class CategoryDto {
    private String id; //栏目ID
    private String name; //栏目名称
    private Integer level; //栏目等级
    private String style; //栏目样式
    private String route;// 路由
    private Boolean show; //是否显示 1：显示 0：不显示
    private String parent; //父级栏目id
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
