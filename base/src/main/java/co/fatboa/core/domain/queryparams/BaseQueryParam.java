package co.fatboa.core.domain.queryparams;

import java.io.Serializable;

/**
 * @Auther: hl
 * @Date: 2018/9/1 09:31
 * @Description: 查询参数基类
 * @Modified By:
 * @Version 1.0
 */
public class BaseQueryParam implements Serializable {

    private String sortField;

    private String sortOrder;

    private Integer pageIndex;

    private Integer pageSize;

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
