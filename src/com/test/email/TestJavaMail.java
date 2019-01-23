package com.test.email;


public class TestJavaMail {

    public static void main(String[] args) {

        MailBean mb = new MailBean();
        mb.setHost("smtp.163.com"); // 设置SMTP主机(163)，若用126，则设为：smtp.126.com   163.177.90.125
        mb.setUsername("YSL"); // 设置发件人邮箱的用户名
        mb.setPassword("jpv1069660408"); // 设置发件人邮箱的密码，需将*号改成正确的密码
        mb.setFrom("17621490798@163.com"); // 设置发件人的邮箱
        mb.setTo("hanley.yan@zenwide.cn"); // 设置收件人的邮箱
        mb.setSubject("测试_JavaMail"); // 设置邮件的主题
        mb.setContent("本邮件中包含三个附件，请检查！"); // 设置邮件的正文

        mb.attachFile("往邮件中添加附件"); // 往邮件中添加附件
        mb.attachFile("往邮件中添加附件");
        mb.attachFile("往邮件中添加附件");

        SendMail sm = new SendMail();
        System.out.println("正在发送邮件...");
        // 发送邮件
        if (sm.sendMail(mb)){
            System.out.println("发送成功!");
        }else{
            System.out.println("发送失败!");
        }
    }
}
