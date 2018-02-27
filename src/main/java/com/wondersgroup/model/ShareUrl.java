package com.wondersgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.view
 * @authorName:wangjiaming
 * @createDate:2018-02-24
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Entity
public class ShareUrl {
    @Id
    @GeneratedValue
    private Long id;
    private String listContent;
    private String url;
    @Column(columnDefinition="INT default 0")
    private String clickNumber;
    private String whyShare;
    private String createUser;
    private Date createDate;
    @Column(columnDefinition="INT default 0")
    private String topDesc;
    @Column(columnDefinition="INT default 0")
    private String removed;

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public String getTopDesc() {
        return topDesc;
    }

    public void setTopDesc(String topDesc) {
        this.topDesc = topDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListContent() {
        return listContent;
    }

    public void setListContent(String listContent) {
        this.listContent = listContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(String clickNumber) {
        this.clickNumber = clickNumber;
    }

    public String getWhyShare() {
        return whyShare;
    }

    public void setWhyShare(String whyShare) {
        this.whyShare = whyShare;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
