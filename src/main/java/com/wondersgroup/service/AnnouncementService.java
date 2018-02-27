package com.wondersgroup.service;

import com.wondersgroup.dao.AnnouncementRepository;
import com.wondersgroup.model.Announcement;
import com.wondersgroup.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AnnouncementService{

    @Autowired
    AnnouncementRepository announcementRepository;

    public List<Announcement> top10Announcement(){
        return announcementRepository.top10Announcement();
    }

    public List<Announcement> allAnnouncement(String userid){
        return announcementRepository.allAnnouncement(userid);
    }

    @Transactional
    public Message removedAnn(String id){
        Message message=new Message();
        try {
            announcementRepository.removeById(id);
            message.setType(true);
        }catch (Exception e){
            message.setType(false);
            e.printStackTrace();
        }
        return message;
    }
}
