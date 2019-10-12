package com.shu.wms.entity;

public class BaseCondition {

    private String[] twoTime;
    private int currentPage;
    private int pageSize;
    private String search;
    private String officeCode;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String[] getTwoTime() {
        return twoTime;
    }

    public void setTwoTime(String[] twoTime) {
        this.twoTime = twoTime;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }
}
