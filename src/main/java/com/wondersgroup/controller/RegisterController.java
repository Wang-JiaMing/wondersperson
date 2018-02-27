package com.wondersgroup.controller;

import com.wondersgroup.model.Register;
import com.wondersgroup.view.RegisterViewModel;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.service.RegisterService;
import com.wondersgroup.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @RequestMapping("/register")
    public String toRegisterPage(Model model) {
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(registerService.findTodayRegister(sysUser.getId()+"")){
            model.addAttribute("isRegister",true);
        }else{
            model.addAttribute("isRegister",false);
        }
        return "register/register";
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public Message userRegister(Register register){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message message=registerService.save(userDetails.getUsername(),register.getRemarks());
        return message;
    }


    @RequestMapping("/userRegisterList")
    @ResponseBody
    public List<RegisterViewModel> userRegisterList(){
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<RegisterViewModel> registerList=registerService.registerList(sysUser.getId()+"");
        return registerList;
    }


    @RequestMapping("/allRegistIndex")
    public String allUserRegisterIndex(){
        System.out.println("111111");
        return "register/allRegister";
    }

    @RequestMapping("/allRegistList")
    @ResponseBody
    public List<RegisterViewModel> allUserRegisterList(){
        List<RegisterViewModel> registerList= null;
        try {
            registerList = registerService.allRegisterList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registerList;
    }

}
