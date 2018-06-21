package com.wondersgroup.controller;

import com.wondersgroup.model.Announcement;
import com.wondersgroup.model.PlanInfo;
import com.wondersgroup.model.ShareUrl;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.service.*;
import com.wondersgroup.utils.DateUtil;
import com.wondersgroup.utils.Message;
import com.wondersgroup.view.MyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    RegisterService registerService;

    @Autowired
    PlanService planService;

    @Autowired
    ShareUrlService shareUrlService;

    @Autowired
    RegisterStatService registerStatService;

    @RequestMapping("/")
    public String saveUser(Model model) throws Exception{
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", sysUser);
        List<Announcement> announcementList = announcementService.top10Announcement();
        model.addAttribute("announcementList", announcementList);
        /**
         * 快速通道
         */
        List<MyList> fastUrl = new ArrayList<>();
        fastUrl.add(new MyList("万达智通","https://linkup.wondersgroup.com/"));
        fastUrl.add(new MyList("JQuery API","http://jquery.cuishifeng.cn"));
        fastUrl.add(new MyList("BootStrap API","https://v3.bootcss.com"));
        fastUrl.add(new MyList("ECharts  API","http://echarts.baidu.com"));
        fastUrl.add(new MyList("Maven Repository","http://mvnrepository.com"));
        fastUrl.add(new MyList("Github","https://github.com"));

        model.addAttribute("fastUrl",fastUrl);


        /**
         * 分享
         */
        List<ShareUrl> shareUrlList = shareUrlService.getAllShareUrl();

        model.addAttribute("shareUrlList",shareUrlList);



        /**
         * 我的清单
         */
        List<MyList> myLists = new ArrayList<>();
        if (registerService.findTodayRegister(sysUser.getId() + "")) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
            MyList myList = new MyList();
            myList.setListContent("今天还没签到[截止时间:" + DateUtil.format(new Date()) + "]");
            myList.setType("2");
            myList.setUrl("/register/register");
            myLists.add(myList);
        }
        List<PlanInfo> planInfos = planService.findIndexPlan(sysUser);
        for (PlanInfo planInfo_qd : planInfos) {
           // SimpleDateFormat sdf_qd = new SimpleDateFormat("yyyy/mm/dd");
            MyList myList = new MyList();
            myList.setListContent(planInfo_qd.getPlanTitle() + "[截止时间:" + DateUtil.format(planInfo_qd.getLastDate()) + "]");
            Date nowDate = new Date();
            if (("2".equals(planInfo_qd.getPlanLevel()) || planInfo_qd.getLastDate().getTime() < nowDate.getTime()) && "0".equals(planInfo_qd.getStatus())) {
                myList.setType("2");
            } else if ("1".equals(planInfo_qd.getPlanLevel()) && planInfo_qd.getLastDate().getTime() >= nowDate.getTime() && "0".equals(planInfo_qd.getStatus())) {
                myList.setType("1");
            } else if ("0".equals(planInfo_qd.getPlanLevel()) && planInfo_qd.getLastDate().getTime() >= nowDate.getTime() && "0".equals(planInfo_qd.getStatus())) {
                myList.setType("0");
            }
            myList.setUrl("/plan/planIndex");
            myLists.add(myList);

        }

        model.addAttribute("myLists", myLists);
        return "index";
    }

    @RequestMapping("/demo")
    public String toDemo()throws Exception{
        registerStatService.mailModal("67");
        return "report";
    }

}
