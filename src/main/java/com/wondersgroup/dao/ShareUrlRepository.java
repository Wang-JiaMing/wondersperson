package com.wondersgroup.dao;

import com.wondersgroup.model.ShareUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.dao
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public interface ShareUrlRepository extends JpaRepository<ShareUrl, Long> {

    public List<ShareUrl> findAllByRemovedOrderByCreateDateDesc(String removed);

    public List<ShareUrl> findByListContentOrUrl(String listContent,String url);

    public ShareUrl findById(Long id);
}
