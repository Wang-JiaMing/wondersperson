package com.wondersgroup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Entity
@Table(schema = "WD_PERSON")
public class SysRole {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
