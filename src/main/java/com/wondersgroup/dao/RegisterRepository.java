package com.wondersgroup.dao;

import com.wondersgroup.model.News;
import com.wondersgroup.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.dao
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface RegisterRepository extends JpaRepository<Register, Long> {

    /**
     * nativeQuery=true 开启sql语句，默认是hql,hql不支持to_char语法
     * @param userid
     * @return
     */
    @Query(value = "select case when to_char(max(register_date),'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd') then 1 else 0 end  from REGISTER r WHERE r.sys_user_id=?1", nativeQuery = true)
    Integer countByRegisterDate(String userid);

    @Query(value = "select ID,register_date, REGISTER_DATE,SYS_USER_ID,REMARKS from REGISTER r WHERE r.sys_user_id=?", nativeQuery = true)
    List<Register> findBySysUserId(String userid);

}
