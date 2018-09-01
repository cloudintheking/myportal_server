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

    private String sortFiled;

    private String sortDirection;

    private Integer pageIndex;

    private Integer pageSize;

    public String getSortFiled() {
        return sortFiled;
    }

    public void setSortFiled(String sortFiled) {
        this.sortFiled = sortFiled;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
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
