package com.wondersgroup.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:任务主表
 **/
@Entity
public class PlanInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String planTitle;
    private String createUser;
    private Date createDate;
    private Date lastDate;
    private String planLable;
    private String planLevel;
    private String status;
    private String overUserName;
    private Date overDate;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysUser> sysUsers;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlanLevel() {
        return planLevel;
    }

    public void setPlanLevel(String planLevel) {
        this.planLevel = planLevel;
    }

    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
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

    public String getPlanLable() {
        return planLable;
    }

    public void setPlanLable(String planLable) {
        this.planLable = planLable;
    }

    public String getOverUserName() {
        return overUserName;
    }

    public void setOverUserName(String overUserName) {
        this.overUserName = overUserName;
    }

    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }
}
