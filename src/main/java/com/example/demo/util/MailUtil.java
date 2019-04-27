package com.example.demo.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.demo.bean.MailBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
@Component
public class MailUtil {
	@Value("${lance.mail.sender}")
	private String MAIL_SENDER;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Configuration configuration; //freemarker
	
	private Logger logger = LoggerFactory.getLogger(MailUtil.class);
	
	// 发送一个简单格式的邮件
	public void sendSimpleMail(MailBean mailBean) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(MAIL_SENDER);
			simpleMailMessage.setTo(mailBean.getRecipient());
			simpleMailMessage.setSubject(mailBean.getSubject());
			simpleMailMessage.setText(mailBean.getContent());
			javaMailSender.send(simpleMailMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败", e.getMessage());
		}
	}
	
	// 发送一个HTML格式的邮件
    public void sendHTMLMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
                    .append("\"<p style='color:#F00'>你是真的太棒了！</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            mimeMessageHelper.setText(sb.toString(), true);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    //发送带附件格式的邮件
    public void sendAttachmentMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent());
            //文件路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
            mimeMessageHelper.addAttachment("mail.png", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    // 发送带静态资源的邮件
    public void sendInlineMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText("<html><body>带静态资源的邮件内容，这个一张IDEA配置的照片:<img src='cid:picture' /></body></html>", true);
            //文件路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
            mimeMessageHelper.addInline("picture", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

      // 发送基于Freemarker模板的邮件
    public void sendTemplateMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("content", mailBean.getContent());
            model.put("title", "标题Mail中使用了FreeMarker");
            Template template = configuration.getTemplate("mail.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeMessageHelper.setText(text, true);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }

    }
}
