package com.wondersgroup.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import java.util.Date;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.utils
 * @authorName:wangjiaming
 * @createDate:2018-02-23
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class SendMail {

    public static String myEmailAccount = "gzwanda020@sina.com";
    public static String myEmailPassword = "gzwanda020";
    public static String myEmailSMTPHost = "smtp.sina.com";
    public final void sendHtmlMail(String title,String content,String addressMail)throws Exception{
        // 不要使用SimpleEmail,会出现乱码问题
        HtmlEmail email = new HtmlEmail();
        // SimpleEmail email = new SimpleEmail();
        try {
            // 这里是SMTP发送服务器的名字：qq的如下：
            email.setHostName(myEmailSMTPHost);
            // 字符编码集的设置
            email.setCharset("utf-8");
            // 收件人的邮箱
            email.addTo(addressMail);
            // 发送人的邮箱
            email.setFrom(myEmailAccount);
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            email.setAuthentication(myEmailAccount, myEmailPassword);
            email.setSubject(title);
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg(content);
            // 发送
            email.send();
            System.out.println("邮件发送成功!");
        } catch (EmailException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败!");
        }
    }

    public final void sendTextMail(String title,String content,String... addressMail) throws Exception
    {
        SimpleEmail mail = new SimpleEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(25);
        mail.setHostName(myEmailSMTPHost);
        // 设置密码验证器
        mail.setAuthentication(myEmailAccount, myEmailPassword);
        // 设置邮件发送者
        mail.setFrom(myEmailAccount);
        // 设置邮件接收者
        mail.addTo(addressMail);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject(title);
        // 设置邮件内容
        mail.setMsg(content);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }

}
