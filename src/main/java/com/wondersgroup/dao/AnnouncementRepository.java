package com.wondersgroup.dao;

import com.wondersgroup.model.Announcement;
import com.wondersgroup.model.Register;
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
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Query(value = "SELECT * FROM (select * from ANNOUNCEMENT r where r.removed=0 ORDER BY CREATE_DATE DESC) WHERE ROWNUM<11", nativeQuery = true)
    List<Announcement> top10Announcement();

    @Query(value = "select * from ANNOUNCEMENT r where sys_user_id=? and r.removed=0 ORDER BY CREATE_DATE DESC", nativeQuery = true)
    List<Announcement> allAnnouncement(String userid);

    @Modifying
    @Query(value="update ANNOUNCEMENT a SET a.removed=1 WHERE ID=?",nativeQuery=true)
    void removeById(String id);

}
