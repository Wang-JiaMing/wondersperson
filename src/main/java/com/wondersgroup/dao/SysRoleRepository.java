package com.wondersgroup.dao;

import com.wondersgroup.model.SysRole;
import com.wondersgroup.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.dao
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface SysRoleRepository extends JpaRepository<SysRole,Long>{

    SysRole findByName(String name);



}
