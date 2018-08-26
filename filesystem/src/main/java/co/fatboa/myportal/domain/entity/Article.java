package co.fatboa.myportal.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: hl
 * @Description: 文章实体类
 * @Date: 15:59 2018/8/22
 * @Modified By:
 * @Version 1.0
 */
@Data
@Document(collection = "article")
public class Article implements Serializable {
    @Id
    private String id;
    @Field
    private String title;
    @Field
    private String description;
    @Field("by_user")
    private String user;
    @Field
    private String url;
    @Field
    private List<String> tags;
    @Field
    private Number likes;
    @DBRef
    private List<Article> childs;
    @DBRef
    private Article parent;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Number getLikes() {
        return likes;
    }

    public void setLikes(Number likes) {
        this.likes = likes;
    }

    public List<Article> getChilds() {
        return childs;
    }

    public void setChilds(List<Article> childs) {
        this.childs = childs;
    }

    public Article getParent() {
        return parent;
    }

    public void setParent(Article parent) {
        this.parent = parent;
    }
}
