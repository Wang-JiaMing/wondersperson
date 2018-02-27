package com.wondersgroup.dao;

import com.wondersgroup.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.dao
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface SysUserRepository extends JpaRepository<SysUser,Long>{

    SysUser findByUsername(String username);

    Long countByUsername(String username);

    SysUser findById(Long id);

}
