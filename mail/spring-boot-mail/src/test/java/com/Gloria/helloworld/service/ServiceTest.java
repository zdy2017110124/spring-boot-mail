package com.Gloria.helloworld.service;

import com.Gloria.helloworld.hello.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Resource
    MailService mailService;
    @Resource
    TemplateEngine templateEngine;
    @Test
    public void sayHelloTest(){
        mailService.sayHello();
    }
    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("xxxx@163.com","This is the first mail","First email test");
    }
    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content="<html>\n"+
                "<body>\n"+
                "<h3> hello world, this is a html mail</h3>\n"+
                "</body>\n"+
                "</html>";
        mailService.sendHtmlMail("xxxx@163.com","This is a html mail", content);
    }
    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        String filePath = "/home/zdy/Documents/attachment";
        mailService.sendAttachmentsMail("xxxx@163.com","This is an attachment mail","Attachment mail test",filePath);
    }
    @Test
    public void sendInlineResourceMailTest(){
        String imgPath = "/home/zdy/Desktop/test.jpeg";
        String rscId = "Gloria001";
        String content = "<html><body> This is an email with picture:<img src=\'cid:"+rscId+"\'></img></body></html>";
        mailService.sendInlineResourceMail("xxxx@163.com","This is a mail with pic",content,imgPath,rscId);
    }
    @Test
    public void testTemplateMailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("id","zdy2017110124");
        String emailContent = templateEngine.process("emailTemplate",context);
        mailService.sendHtmlMail("xxxx@163.com","This a template email",emailContent);
    }
}
