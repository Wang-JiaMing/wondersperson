package com.wondersgroup.service;

import com.wondersgroup.dao.SysUserRepository;
import com.wondersgroup.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.security
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class CustomUserService implements UserDetailsService{

    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s){
        SysUser user=userRepository.findByUsername(s+"@wondersgroup.com");
        if(user==null){
            throw new UsernameNotFoundException("can't found user,please try again");
        }
        return user;
    }
}
