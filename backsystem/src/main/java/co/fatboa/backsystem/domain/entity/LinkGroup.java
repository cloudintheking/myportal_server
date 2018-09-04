package co.fatboa.backsystem.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author: hl
 * @Description: 链接组
 * @Date: 21:41 2018/8/27
 * @Modified By:
 * @Version 1.0
 */
@Data
@Document
public class LinkGroup {
    @Id
    private String id;
    private String name; //链接组名
    private List<Link> links; //链接列组


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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
