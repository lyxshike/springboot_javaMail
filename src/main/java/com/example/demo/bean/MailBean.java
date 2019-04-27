package com.example.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailBean implements Serializable {
	private String recipient;
	private String subject;
	private String content;
}
