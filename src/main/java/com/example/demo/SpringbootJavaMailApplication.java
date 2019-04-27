package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.bean.MailBean;
import com.example.demo.util.MailUtil;

@SpringBootApplication
@EnableScheduling
public class SpringbootJavaMailApplication {

	@Autowired
	MailUtil mailUtil;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJavaMailApplication.class, args);
	}

	/*
	 * 搞个线程池，然后搞个邮件爆炸。  6不6！！！！
	 * */
	
	@Scheduled(fixedRate=1000 * 30)
	public void run() {
		MailBean mailBean = new MailBean();
		mailBean.setRecipient("13468682371@163.com");
		mailBean.setSubject("老子在定时给你娃发邮件呢");
		mailBean.setContent("就问你娃怕不怕");
		mailUtil.sendSimpleMail(mailBean);
	}
}
