package com.wondersgroup.controller;

import com.wondersgroup.model.PlanInfo;
import com.wondersgroup.model.ShareUrl;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.service.PlanService;
import com.wondersgroup.service.ShareUrlService;
import com.wondersgroup.utils.Message;
import com.wondersgroup.view.PlanInfoViewModel;
import com.wondersgroup.view.UserPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping("/shareUrl")
public class ShareUrlController {

    @Autowired
    ShareUrlService shareUrlService;


    @RequestMapping("/addurl")
    @ResponseBody
    public Message addUrl(ShareUrl shareUrl) {
        Message message = new Message();
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (shareUrlService.checkShare(shareUrl) < 1) {
            try {
                shareUrl.setUrl("http://" + shareUrl.getUrl());
                shareUrl.setCreateUser(sysUser.getName());
                shareUrlService.saveUrl(shareUrl);
                message.setType(true);
            } catch (Exception e) {
                message.setType(false);
                e.printStackTrace();
            }
        }else{
            message.setType(false);
            message.setMsg("你添加的分享已存在");
        }
        return message;
    }


    @RequestMapping("/updateClick")
    @ResponseBody
    public Message updateClickNumber(ShareUrl shareUrl) {
        Message message = new Message();
        try{
            shareUrlService.updateShare(shareUrl);
            message.setType(true);
        }catch (Exception e){
            message.setType(false);
        }
        return message;
    }
}
