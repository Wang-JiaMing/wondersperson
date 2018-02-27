package com.wondersgroup.view;

import com.wondersgroup.model.SysUser;

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
public class PlanInfoViewModel {

    private String id;
    private String planTitle;
    private String createUser;
    private String createDate;
    private String lastDate;
    private String planLable;
    private String planLevel;
    private String status;
    private String styleClass;
    private String personList;
    private String overDate;
    private String overUserName;
    private String addUserButton;

    public String getAddUserButton() {
        return addUserButton;
    }

    public void setAddUserButton(String addUserButton) {
        this.addUserButton = addUserButton;
    }

    public String getOverDate() {
        return overDate;
    }

    public void setOverDate(String overDate) {
        this.overDate = overDate;
    }

    public String getOverUserName() {
        return overUserName;
    }

    public void setOverUserName(String overUserName) {
        this.overUserName = overUserName;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getPersonList() {
        return personList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPersonList(String personList) {
        this.personList = personList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getPlanLable() {
        return planLable;
    }

    public void setPlanLable(String planLable) {
        this.planLable = planLable;
    }

    public String getPlanLevel() {
        return planLevel;
    }

    public void setPlanLevel(String planLevel) {
        this.planLevel = planLevel;
    }
}
