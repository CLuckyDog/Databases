package com.databases.databases.common.bean.view;


public class BaseQueryView extends AbstractView {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalIndex;
    private Long totalSize;

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

    public Integer getTotalIndex() {
        return totalIndex;
    }

    public void setTotalIndex(Integer totalIndex) {
        this.totalIndex = totalIndex;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }
}
