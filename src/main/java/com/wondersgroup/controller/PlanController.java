package com.wondersgroup.controller;

import com.wondersgroup.model.PlanInfo;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.service.PlanService;
import com.wondersgroup.service.UserService;
import com.wondersgroup.utils.DateUtil;
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
 * @other:签到
 **/
@Controller
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @RequestMapping("/planIndex")
    public String toPlanPage(Model model) throws Exception {
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PlanInfo> planInfoList = planService.findMyPlan(sysUser);
        List<PlanInfoViewModel> planInfoViewModelList = new ArrayList<>();
        for (PlanInfo planInfo : planInfoList) {
            PlanInfoViewModel planInfoViewModel = new PlanInfoViewModel();
            planInfoViewModel.setId(planInfo.getId() + "");
            planInfoViewModel.setPlanLable(planInfo.getPlanLable());
            planInfoViewModel.setPlanTitle(planInfo.getPlanTitle());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            planInfoViewModel.setLastDate(DateUtil.format(planInfo.getLastDate()));
            planInfoViewModel.setCreateUser(planInfo.getCreateUser());
            StringBuffer sb = new StringBuffer();
            for (SysUser user : planInfo.getSysUsers()) {
                sb.append(user.getName() + ";");
            }
            planInfoViewModel.setPersonList(sb.toString());
            planInfoViewModel.setPlanLevel(planInfo.getPlanLevel());
            planInfoViewModel.setStatus(planInfo.getStatus());
            Date nowDate = new Date();
            if (("2".equals(planInfo.getPlanLevel()) || planInfo.getLastDate().getTime() < nowDate.getTime()) && "0".equals(planInfo.getStatus())) {
                planInfoViewModel.setStyleClass("2");
            } else if ("0".equals(planInfo.getStatus())) {
                planInfoViewModel.setStyleClass("1");
            } else if ("1".equals(planInfo.getStatus())) {
                planInfoViewModel.setStyleClass("0");
            }
            if(planInfo.getCreateUser().equals(sysUser.getName())){
                if(planInfo.getStatus().equals("0")) {
                    planInfoViewModel.setAddUserButton("0");//true
                }else{
                    planInfoViewModel.setAddUserButton("1");//false
                }
            }else{
                planInfoViewModel.setAddUserButton("1");//false
            }

            if (planInfo.getOverDate() != null) {
                planInfoViewModel.setOverDate(DateUtil.format(planInfo.getOverDate()));
            }
            planInfoViewModel.setOverUserName(planInfo.getOverUserName());
            planInfoViewModelList.add(planInfoViewModel);
        }
        model.addAttribute("planList", planInfoViewModelList);
        return "plan/myPlan";
    }

    @RequestMapping("/addPlan")
    @ResponseBody
    public Message addPlan(PlanInfoViewModel planInfoViewModel) {
        Message message = new Message();
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            planService.savePlanInfo(sysUser, planInfoViewModel);
            message.setType(true);
        } catch (Exception e) {
            message.setType(false);
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping("/overPlan")
    @ResponseBody
    public Message overPlan(String id) {
        Message message = new Message();
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            planService.overPlan(id, sysUser);
            message.setType(true);
        } catch (Exception e) {
            message.setType(false);
            e.printStackTrace();
        }
        return message;
    }


    @RequestMapping("/planUserList")
    @ResponseBody
    public List<UserPlan> planUserList(String id) {
        return planService.planUserList(id);
    }

    @RequestMapping("/planAddUser")
    @ResponseBody
    public Message planUserList(String id, String userid) {
        Message message = new Message();
        String[] useridList = userid.split(",");
        try {
            planService.addUser(id, useridList);
            message.setType(true);
        } catch (Exception e) {
            message.setType(false);
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping("/sendMail")
    @ResponseBody
    public Message sendMail(String id) {
        Message message = new Message();
        try {
            planService.sendMail(id);
            message.setType(true);
        } catch (Exception e) {
            message.setType(false);
            e.printStackTrace();
        }
        return message;
    }

}
