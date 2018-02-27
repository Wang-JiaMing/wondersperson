package com.wondersgroup.service;

import com.wondersgroup.dao.AnnouncementRepository;
import com.wondersgroup.dao.ShareUrlRepository;
import com.wondersgroup.model.Announcement;
import com.wondersgroup.model.ShareUrl;
import com.wondersgroup.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.security
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Service
public class ShareUrlService {

    @Autowired
    ShareUrlRepository shareUrlRepository;


    public List<ShareUrl> getAllShareUrl(){
        return shareUrlRepository.findAllByRemovedOrderByCreateDateDesc("0");
    }

    public void saveUrl(ShareUrl shareUrl){
        shareUrl.setClickNumber("0");
        shareUrl.setRemoved("0");
        shareUrl.setTopDesc("0");
        shareUrl.setCreateDate(new Date());
        shareUrlRepository.save(shareUrl);
    }

    public Integer checkShare(ShareUrl shareUrl){
        List<ShareUrl> shareUrlList=shareUrlRepository.findByListContentOrUrl(shareUrl.getListContent(),shareUrl.getUrl());
        return shareUrlList.size();
    }

    public void updateShare(ShareUrl shareUrl){
        ShareUrl su=shareUrlRepository.findById(shareUrl.getId());
        Integer newClickNumber=Integer.valueOf(su.getClickNumber())+1;
        su.setClickNumber(newClickNumber+"");
        shareUrlRepository.save(su);
    }
}
