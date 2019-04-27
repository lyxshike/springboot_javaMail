# springboot_javaMail

JavaMailSender， springboot中自带的邮件发送辅助类
  javaMialSender.send(simpleMailMessage); 就可以完成简单的邮件发送。
  如果发送HTML格式的邮件，或者附件的，则要借用mimeMessageHelper。
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
