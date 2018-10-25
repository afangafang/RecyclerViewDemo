package com.tms.recyclerviewdemo;

/**
 * Created by Administrator on 2018/10/25.
 */

public class NewsBean {
    private String newsTitle;
    private boolean hasRead;

    public NewsBean() {
    }

    public NewsBean(String newsTitle, boolean hasRead) {
        this.newsTitle = newsTitle;
        this.hasRead = hasRead;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }
}
