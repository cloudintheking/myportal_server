package co.fatboa.backsystem.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: hl
 * @Description: 链接类
 * @Date: 21:53 2018/8/27
 * @Modified By:
 * @Version 1.0
 */
@Data
@Document
public class Link {
    @Id
    private String id;
    private String name; //链接名
    private String url; //链接地址
    @DBRef
    private LinkGroup group; //链接组

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LinkGroup getGroup() {
        return group;
    }

    public void setGroup(LinkGroup group) {
        this.group = group;
    }
}
