package com.wondersgroup.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:新闻资讯
 **/
@Entity
@Table(schema = "WD_PERSON")
public class Announcement {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    /**
     * 数据来源
     */
    @Lob
    private String content;
    private Date createDate;
    private String sysUserId;
    private String sysUserName;
    private Integer readNumber;
    @Column(columnDefinition="INT default 0")
    private Integer removed;

    /**
     * 0未发布
     * 1已发布
     */
    @Column(columnDefinition="INT default 0")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }
}
