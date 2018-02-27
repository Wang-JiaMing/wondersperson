package com.wondersgroup.controller;

import com.wondersgroup.model.Announcement;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.service.AnnouncementService;
import com.wondersgroup.service.NewsService;
import com.wondersgroup.service.UserService;
import com.wondersgroup.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.controller
 * @authorName:wangjiaming
 * @createDate:2018-02-09
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Controller
@RequestMapping("/ann")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @RequestMapping("/myAnn")
    public String annIndex(Model model) {
        return "announcement/myAnnouncement";
    }

    @RequestMapping("/myAnnList")
    @ResponseBody
    public List<Announcement> getAnnouncementList() {
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return announcementService.allAnnouncement(sysUser.getId()+"");
    }

    @RequestMapping("/removeAnn")
    @ResponseBody
    public Message getAnnouncementList(Announcement announcement) {
        Message message=announcementService.removedAnn(announcement.getId()+"");
        return message;
    }

}
