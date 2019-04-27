package com.example.demo.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.bean.MailBean;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailUtilTest {

	@Autowired
	MailUtil mailUtil;
	
	@Test
	public void testSendSimpleMail() {
		MailBean mailBean = new MailBean();
        mailBean.setRecipient("13468682371@163.com");
        mailBean.setSubject("测试SpringBootMail");
        mailBean.setContent("测试SpringBootMail");

        mailUtil.sendSimpleMail(mailBean);
	}

	@Test
	public void testSendHTMLMail() {
		MailBean mailBean = new MailBean();
        mailBean.setRecipient("13468682371@163.com");
        mailBean.setSubject("测试SpringBootMailHTML");
        mailBean.setContent("测试SpringBootMailHTML");
        mailUtil.sendHTMLMail(mailBean);
	}

	@Test
	public void testSendAttachmentMail() {
		MailBean mailBean = new MailBean();
        mailBean.setRecipient("13468682371@163.com");
        mailBean.setSubject("测试SpringBootMail附件");
        mailBean.setContent("测试SpringBootMail附件");
        mailUtil.sendAttachmentMail(mailBean);
	}

	@Test
	public void testSendInlineMail() {
		MailBean mailBean = new MailBean();
        mailBean.setRecipient("13468682371@163.com");
        mailBean.setSubject("测试SpringBootMail Inline subject");
        mailBean.setContent("测试SpringBootMail Inline content");
        mailUtil.sendAttachmentMail(mailBean);
	}

	@Test
	public void testSendTemplateMail() {
		MailBean mailBean = new MailBean();
        mailBean.setRecipient("13468682371@163.com");
        mailBean.setSubject("测试SpringBootMail Template subject");
        mailBean.setContent("测试SpringBootMail Template content");
        mailUtil.sendTemplateMail(mailBean);
	}

}
