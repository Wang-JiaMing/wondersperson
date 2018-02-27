package com.wondersgroup.view;

import java.util.Date;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.model
 * @authorName:wangjiaming
 * @createDate:2018-02-23
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class RegisterViewModel {
    private String id;
    private Date   startDate;
    private Date   endDate;
    private String name;
    private String location;

    private String registerDate;
    private String registerTime;
    private String remark;

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
