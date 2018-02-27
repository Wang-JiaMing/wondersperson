package com.wondersgroup.service;

import com.wondersgroup.dao.RegisterRepository;
import com.wondersgroup.dao.SysUserRepository;
import com.wondersgroup.model.Register;
import com.wondersgroup.utils.DateUtil;
import com.wondersgroup.view.RegisterViewModel;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.service
 * @authorName:wangjiaming
 * @createDate:2018-02-11
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Service
public class RegisterService {


    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    public boolean findTodayRegister(String userid) {
        Message msg = new Message();
        Integer registerMaxTime = registerRepository.countByRegisterDate(userid);
        if (registerMaxTime > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public Message save(String username,String remarks) {
        Message message=new Message();
        Register register = new Register();
        SysUser sysUser = sysUserRepository.findByUsername(username);
        register.setSysUserId(sysUser.getId() + "");
        register.setRegisterDate(new Date());
        register.setRemarks(remarks);
        try {
            registerRepository.save(register);
            message.setType(true);
        }catch (Exception e){
            message.setType(false);
            message.setMsg(e.getLocalizedMessage());
        }
        return message;
    }

    public List<RegisterViewModel> registerList(String userid) {
        List<Register> registerList=registerRepository.findBySysUserId(userid);
        List<RegisterViewModel> registerViewModelList=new ArrayList<>();
        for(Register register:registerList){
            RegisterViewModel registerViewModel=new RegisterViewModel();
            registerViewModel.setId(register.getId()+"");
            SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
            registerViewModel.setLocation("签到时间:"+sdfTime.format(register.getRegisterDate()));
            if(register.getRemarks()!=null) {
                registerViewModel.setName(register.getRemarks());
            }else{
                registerViewModel.setName("无备注");
            }
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            try {
                registerViewModel.setStartDate(sdfDate.parse(sdfDate.format(register.getRegisterDate())));
                registerViewModel.setEndDate(sdfDate.parse(sdfDate.format(register.getRegisterDate())));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            registerViewModelList.add(registerViewModel);
        }
        return registerViewModelList;
    }

    public List<RegisterViewModel> allRegisterList() throws Exception{
        List<Register> registerList=registerRepository.findAll();
        List<RegisterViewModel> registerViewModelList=new ArrayList<>();
        for(Register register:registerList){
            SysUser sysUser=sysUserRepository.findById(Long.valueOf(register.getSysUserId()));
            RegisterViewModel registerViewModel=new RegisterViewModel();
            registerViewModel.setName(sysUser.getName());
            registerViewModel.setRegisterDate(DateUtil.format(register.getRegisterDate()));
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
            registerViewModel.setRegisterTime(sdfTime.format(register.getRegisterDate()));
            registerViewModel.setRemark(register.getRemarks());
            registerViewModelList.add(registerViewModel);
        }
        return registerViewModelList;
    }

}
