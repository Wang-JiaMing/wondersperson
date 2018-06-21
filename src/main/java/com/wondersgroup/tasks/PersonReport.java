package com.wondersgroup.tasks;

import com.wondersgroup.service.RegisterStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @projectName:datareport
 * @packageName:com.wondersgroup.datareport.tasks
 * @authorName:wangjiaming
 * @createDate:2018-03-06
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Component
public class PersonReport {

    @Autowired
    RegisterStatService registerStatService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void personReport() {
        try {
            registerStatService.sendMail();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
