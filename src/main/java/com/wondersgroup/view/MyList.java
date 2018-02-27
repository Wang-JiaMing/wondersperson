package com.wondersgroup.view;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.view
 * @authorName:wangjiaming
 * @createDate:2018-02-24
 * @editor:IntelliJ IDEA
 * @other:我的清单
 **/
public class MyList {
    private String listContent;
    private String url;
    private String type;//0低级；1中级；2高级

    public MyList() {
    }

    public MyList(String listContent, String url) {
        this.listContent = listContent;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getListContent() {
        return listContent;
    }

    public void setListContent(String listContent) {
        this.listContent = listContent;
    }
}
