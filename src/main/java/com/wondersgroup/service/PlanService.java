package com.wondersgroup.service;

import com.wondersgroup.dao.PlanInfoRepository;
import com.wondersgroup.dao.SysUserRepository;
import com.wondersgroup.model.PlanInfo;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.utils.DateUtil;
import com.wondersgroup.utils.SendMail;
import com.wondersgroup.view.PlanInfoViewModel;
import com.wondersgroup.view.UserPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.service
 * @authorName:wangjiaming
 * @createDate:2018-02-24
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Service
public class PlanService {

    @Autowired
    PlanInfoRepository planInfoRepository;
    @Autowired
    SysUserRepository sysUserRepository;


    @Transactional(rollbackFor = Exception.class)
    public void savePlanInfo(SysUser sysUser, PlanInfoViewModel planInfoViewModel)throws Exception {
        List<SysUser> sysUserList = new ArrayList<>();
        sysUserList.add(sysUser);
        PlanInfo planInfo = new PlanInfo();
        planInfo.setSysUsers(sysUserList);
        planInfo.setCreateDate(new Date());
        planInfo.setCreateUser(sysUser.getName());
        planInfo.setPlanTitle(planInfoViewModel.getPlanTitle());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        planInfo.setLastDate(DateUtil.parse(planInfoViewModel.getLastDate()));
        planInfo.setPlanLable(planInfoViewModel.getPlanLable());
        planInfo.setPlanLevel(planInfoViewModel.getPlanLevel());
        planInfo.setStatus("0");
        planInfoRepository.save(planInfo);
    }

    public List<PlanInfo> findMyPlan(SysUser sysUser) {
        List<PlanInfo> planInfos = planInfoRepository.findAllBySysUsersOrderByStatusAscPlanLevelDescLastDateAsc(sysUser);
        return planInfos;
    }


    public List<PlanInfo> findIndexPlan(SysUser sysUser) {
        List<PlanInfo> planInfos = planInfoRepository.findBySysUsersAndAndStatusOrderByPlanLevelDescLastDateAsc(sysUser, "0");
        return planInfos;
    }

    @Transactional(rollbackFor = Exception.class)
    public void overPlan(String id, SysUser user) {
        planInfoRepository.overPlan(user.getName(), Long.valueOf(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(String id,String userid[]){
        List<SysUser> sysUsers=new ArrayList<>();
        for(int i=0;i<userid.length;i++){
            sysUsers.add(sysUserRepository.findById(Long.valueOf(userid[i])));
        }
        PlanInfo planInfo=planInfoRepository.findById(Long.valueOf(id));
        planInfo.setSysUsers(sysUsers);
        planInfoRepository.save(planInfo);
    }

    public List<UserPlan> planUserList(String id){
        PlanInfo planInfo=planInfoRepository.findById(Long.valueOf(id));
        List<UserPlan> userPlanList=new ArrayList<>();
        for(SysUser user:planInfo.getSysUsers()){
            UserPlan userPlan=new UserPlan();
            userPlan.setId(user.getId()+"");
            userPlan.setEmail(user.getUsername());
            userPlan.setName(user.getName());
            userPlan.setNo(user.getUserno());
            userPlan.setType("1");
            userPlanList.add(userPlan);
        }
        List<Object[]> otherUser=planInfoRepository.otherUser(id);
        for(Object[] user:otherUser){
            if(!"admin".equals(user[3]+"")) {
                UserPlan userPlan = new UserPlan();
                userPlan.setId(user[0] + "");
                userPlan.setEmail(user[5] + "");
                userPlan.setName(user[3] + "");
                userPlan.setNo(user[6] + "");
                userPlan.setType("0");
                userPlanList.add(userPlan);
            }
        }
        return userPlanList;
    }

    public void sendMail(String id) throws Exception{
        PlanInfo planInfo=planInfoRepository.findById(Long.valueOf(id));
        StringBuffer title=new StringBuffer();
        if(planInfo.getPlanLable()!=null){
            title.append(planInfo.getPlanLable());
        }
        title.append("[系统任务提醒]");
        StringBuffer htmlContent=new StringBuffer();
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        htmlContent.append("<span align=\"center\"><h2>"+planInfo.getPlanTitle()+"</h2></span><br/>");
        htmlContent.append("<span>任务截止时间："+ DateUtil.format(planInfo.getLastDate())+"</span><br/>");
        htmlContent.append("<span>任务发送人："+ planInfo.getCreateUser()+"</span><br/>");
        htmlContent.append("<hr/>");
        htmlContent.append("<div align=\"right\"><a href=\"http://"+hostAddress+"/plan/planIndex\">进行系统</a><br/></div>");
        htmlContent.append("<div align=\"right\"><span>本邮件是系统邮件，请勿回复</span><br/></div>");
        for(SysUser user:planInfo.getSysUsers()){
            if(!user.getName().equals(planInfo.getCreateUser())){
                SendMail sendMail=new SendMail();
                sendMail.sendHtmlMail(title.toString(),htmlContent.toString(),user.getUsername());
            }
        }

    }
}
