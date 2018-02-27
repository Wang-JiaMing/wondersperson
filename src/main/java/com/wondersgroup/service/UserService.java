package com.wondersgroup.service;

import com.wondersgroup.dao.SysRoleRepository;
import com.wondersgroup.dao.SysUserRepository;
import com.wondersgroup.model.SysRole;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.utils.Md5Util;
import com.wondersgroup.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.service
 * @authorName:wangjiaming
 * @createDate:2018-02-09
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Service
public class UserService {
    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SysRoleRepository sysRoleRepository;

    @Transactional
    public Message register(SysUser user){
        Message msg=new Message();
        Long userLong=sysUserRepository.countByUsername(user.getUsername()+"@wondersgroup.com");

        if(userLong<1) {
            List<SysRole> sysRoleList = new ArrayList<SysRole>();
            if ("admin".equals(user.getUsername())) {
                SysRole sysRoleUser = sysRoleRepository.findByName("ROLE_USER");
                SysRole sysRoleAdmin = sysRoleRepository.findByName("ROLE_ADMIN");
                sysRoleList.add(sysRoleAdmin);
                sysRoleList.add(sysRoleUser);
            } else {
                SysRole sysRoleUser = sysRoleRepository.findByName("ROLE_USER");
                sysRoleList.add(sysRoleUser);
            }
            try {
                user.setUsername(user.getUsername() + "@wondersgroup.com");
                user.setPassword(Md5Util.getMD5String(user.getPassword()));
                user.setIsenabled(true);
                user.setCreateDate(new Date());
                user.setRoles(sysRoleList);
                sysUserRepository.save(user);
                msg.setType(true);
                return msg;
            } catch (Exception e) {
                msg.setType(false);
                msg.setMsg(e.getLocalizedMessage());
                return msg;
            }
        }else{
            msg.setType(false);
            msg.setMsg("当前邮箱已注册，请联系管理员");
            return msg;
        }
    }

    public List<SysUser> getUserList(){
       return sysUserRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    public void updateUserPasser(SysUser sysUser){
        sysUserRepository.save(sysUser);
    }
}
