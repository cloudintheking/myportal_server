package co.fatboa.backsystem.domain.dto;

import co.fatboa.backsystem.domain.entity.LinkGroup;
import lombok.Data;

/**
 * @Auther: hl
 * @Date: 2018/9/2 11:29
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Data
public class LinkDto {
    private String id; //链接id
    private String name; //链接名
    private String url; //链接地址
    private String group; //链接组id

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
