package com.wondersgroup.controller;

import com.wondersgroup.model.SysUser;
import com.wondersgroup.service.UserService;
import com.wondersgroup.utils.Md5Util;
import com.wondersgroup.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "register")
    @ResponseBody
    public Message saveUser(SysUser user) {
        Message msg = userService.register(user);
        return msg;
    }

    @RequestMapping(value = "personIndex")
    public String getUserList(Model model) {
        return "personList/personList";
    }

    @ResponseBody
    @RequestMapping(value = "personList")
    public List<SysUser> getUserList() {
        return userService.getUserList();
    }


    @RequestMapping(value = "updatePasswordIndex")
    public String updatePasswordIndex() {
        return "person/updatePassword";
    }

    @RequestMapping(value = "updatePassword")
    @ResponseBody
    public Message updatePasswordIndex(String oldPassword,String password) {
        Message msg = new Message();
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(sysUser.getPassword().equals(Md5Util.getMD5String(oldPassword))){
            sysUser.setPassword(Md5Util.getMD5String(password));
            userService.updateUserPasser(sysUser);
            msg.setType(true);
        }else{
            msg.setType(false);
            msg.setMsg("原密码错误");
        }
        return msg;
    }
}
