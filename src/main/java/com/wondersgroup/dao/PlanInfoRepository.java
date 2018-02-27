package com.wondersgroup.dao;

import com.wondersgroup.model.PlanInfo;
import com.wondersgroup.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.dao
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface PlanInfoRepository extends JpaRepository<PlanInfo, Long> {

    public List<PlanInfo> findAllBySysUsersOrderByStatusAscPlanLevelDescLastDateAsc(SysUser sysUser);

    public List<PlanInfo> findBySysUsersAndAndStatusOrderByPlanLevelDescLastDateAsc(SysUser sysUser, String status);

    @Modifying
    @Query(value = "update PLAN_INFO i set i.status='1',i.over_date=sysdate,i.over_user_name=? where i.id=? ", nativeQuery = true)
    public void overPlan(String userName, Long id);


    public PlanInfo findById(Long id);

    @Query(nativeQuery = true, value = "select * from SYS_USER s where s.id not in(Select sys_users_id from plan_info_sys_users where plan_info_id=?) and isenabled=1 ")
    public List<Object[]> otherUser(String planId);
}
