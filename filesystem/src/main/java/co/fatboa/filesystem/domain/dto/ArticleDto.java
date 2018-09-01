package co.fatboa.filesystem.domain.dto;

import lombok.Data;

/**
 * @Auther: hl
 * @Date: 2018/9/1 10:55
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Data
public class ArticleDto {
    private String id;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
