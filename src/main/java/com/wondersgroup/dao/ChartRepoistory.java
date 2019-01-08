package com.wondersgroup.dao;

import com.wondersgroup.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.dao
 * @authorName:wangjiaming
 * @createDate:2019-01-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface ChartRepoistory extends JpaRepository<News,Long> {

    @Query(value ="select to_char(REGISTER_DATE,'yyyy-mm-dd') from WD_PERSON.REGISTER r where r.SYS_USER_ID=? order by REGISTER_DATE asc", nativeQuery = true)
    List<String> getXAxisDate(String uid);

    @Query(value ="select to_number(to_char(REGISTER_DATE,'hh24.mi')) from WD_PERSON.REGISTER r where r.SYS_USER_ID=? order by REGISTER_DATE asc", nativeQuery = true)
    List<String> getSeriesDate(String uid);
}

