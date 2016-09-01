package com.search.www;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class SearchResult {
    private int page;

    private int total;
    private List<Webpage> webpages;
    private int pageSize;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Webpage> getWebpages() {
        return webpages;
    }

    public void setWebpages(List<Webpage> webpages) {
        this.webpages = webpages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
