package com.wondersgroup.service;

import com.wondersgroup.dao.RegisterRepository;
import com.wondersgroup.dao.SysUserRepository;
import com.wondersgroup.model.Register;
import com.wondersgroup.model.SysUser;
import com.wondersgroup.utils.*;
import com.wondersgroup.view.RegisterViewModel;
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
public class RegisterStatService {


    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    public void mailModal(String userid) throws Exception {
        SysUser sysUser = sysUserRepository.findById(Long.valueOf(userid));
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append(PersonReportMail.getHead());
        htmlBuffer.append(
                PersonReportMail.contentJm(registerRepository.findNo1BySysUserId(userid),
                        registerRepository.findMinRegisterDateBySysUserId(userid),
                        registerRepository.findMaxRegisterDateBySysUserId(userid),
                        registerRepository.findNo1PersonName(),
                        registerRepository.findNo1PersonNb(),
                        registerRepository.findMinPersonName(),
                        registerRepository.findMinPersonRegisterDate()));
        List<String> server = registerRepository.findServerDateList(userid);
        List<String> regist = registerRepository.findRegisterDateList(userid);
        List<PersonReportList> personReportLists = new ArrayList<>();
        for (int i = 0; i < server.size(); i++) {
            PersonReportList personReportList = new PersonReportList();
            personReportList.setServerDate(server.get(i));
            personReportList.setRegisterDate(regist.get(i));
            personReportLists.add(personReportList);
        }
        htmlBuffer.append(PersonReportMail.contentList(personReportLists));
        System.out.println(htmlBuffer.toString());
        SendMail sendMail = new SendMail();
        sendMail.sendHtmlMail("个人签到月报", htmlBuffer.toString(), sysUser.getUsername());
    }

    public void sendMail() throws Exception {
        List<String> userIdList = registerRepository.findPersonList();
        for (String userId : userIdList) {
            mailModal(userId);
        }
    }


}
