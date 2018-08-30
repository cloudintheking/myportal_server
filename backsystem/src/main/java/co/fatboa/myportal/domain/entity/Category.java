package co.fatboa.myportal.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @Author: hl
 * @Description: 栏目类
 * @Date: 20:14 2018/8/27
 * @Modified By:
 * @Version 1.0
 */
@Data
@Document
public class Category {
    @Id
    private String id; //栏目id
    private String name; //栏目名称
    private String level; //栏目等级
    private String style; //栏目样式
    private String route;// 路由
    private String show; //是否显示 1：显示 0：不显示
    @DBRef
    private Category parent; // 父栏目
    @DBRef
    private List<Category> childs;// 子栏目

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChilds() {
        return childs;
    }

    public void setChilds(List<Category> childs) {
        this.childs = childs;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}