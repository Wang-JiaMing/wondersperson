package com.wondersgroup.dao;

import com.wondersgroup.model.News;
import com.wondersgroup.model.Register;
import com.wondersgroup.utils.PersonReportList;
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
     *
     * @param userid
     * @return
     */
    @Query(value = "select case when to_char(max(register_date),'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd') then 1 else 0 end  from REGISTER r WHERE r.sys_user_id=?1", nativeQuery = true)
    Integer countByRegisterDate(String userid);

    @Query(value = "select ID,register_date, REGISTER_DATE,SYS_USER_ID,REMARKS from REGISTER r WHERE r.sys_user_id=?", nativeQuery = true)
    List<Register> findBySysUserId(String userid);

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "select count(1) From(\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            " order by m.server_date asc)tmp where sys_user_id=? and pm='1'", nativeQuery = true)
    String findNo1BySysUserId(String userid);

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "select min(register_date) From(\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            " order by m.server_date asc)tmp where sys_user_id=?", nativeQuery = true)
    String findMinRegisterDateBySysUserId(String userid);

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "select max(register_date) From(\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            " order by m.server_date asc)tmp where sys_user_id=?", nativeQuery = true)
    String findMaxRegisterDateBySysUserId(String userid);

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "select name From(\n" +
            "select name,count(1) nb from(\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            " )tmp where pm=1\n" +
            " group by name\n" +
            " order by nb desc)t where rownum<2", nativeQuery = true)
    String findNo1PersonName();

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "select nb From(\n" +
            "select name,count(1) nb from(\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            " )tmp where pm=1\n" +
            " group by name\n" +
            " order by nb desc)t where rownum<2", nativeQuery = true)
    String findNo1PersonNb();

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "select name From(\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            "    order by pt.register_date asc)t where rownum<2", nativeQuery = true)
    String findMinPersonName();

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "select register_date From(\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            "    order by pt.register_date asc)t where rownum<2", nativeQuery = true)
    String findMinPersonRegisterDate();

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "   select server_date serverDate from (\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            "    where sys_user_id=?\n" +
            "    order by m.server_date asc)", nativeQuery = true)
    List<String> findServerDateList(String userId);

    @Query(value = "with maxDayList as\n" +
            " (select distinct to_char(t.register_date, 'yyyy-mm-dd') server_date\n" +
            "    from REGISTER t\n" +
            "   where to_char(t.register_date, 'yyyy-mm') =\n" +
            "         to_char(add_months(sysdate, -1), 'yyyy-mm')\n" +
            "   order by server_date asc)\n" +
            "   select register_date registerDate from (\n" +
            "select m.server_date,\n" +
            "       pt.register_date,\n" +
            "       pt.sys_user_id,\n" +
            "       pt.name,\n" +
            "       rank() over(PARTITION BY pt.server_date ORDER BY PT.register_date asc) pm\n" +
            "  From maxDayList m\n" +
            "  left join (select to_char(t.register_date, 'yyyy-mm-dd') server_date,\n" +
            "                    to_char(t.register_date, 'hh24:mi:ss') register_date,\n" +
            "                    t.sys_user_id,\n" +
            "                    (select u.name From sys_user u where u.id = t.sys_user_id) name\n" +
            "               from REGISTER t) pt\n" +
            "    on m.server_date = pt.server_date\n" +
            "    where sys_user_id=?\n" +
            "    order by m.server_date asc)", nativeQuery = true)
    List<String> findRegisterDateList(String userId);

    @Query(value = "select distinct sys_user_id\n" +
            "  from register\n" +
            " where to_char(register_date, 'yyyy-mm') =\n" +
            "       to_char(add_months(sysdate, '-1'), 'yyyy-mm')", nativeQuery = true)
    List<String> findPersonList();
}
