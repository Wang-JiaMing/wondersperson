package com.wondersgroup.dao;

import com.wondersgroup.model.News;
import com.wondersgroup.model.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface NewsRepository extends JpaRepository<News,Long>{
    /**
     * nativeQuery = true
     * @return
     */
    @Query("select max(batchNo) from News")
    String findByMaxBatchNo();

    List<News> findByBatchNo(String batchNo);



}
