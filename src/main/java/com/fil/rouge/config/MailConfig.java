//package com.fil.rouge.config;
//
//
//import jakarta.mail.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class MailConfig {
//
//    @Value("${spring.mail.username}")
//    private String from;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.port}")
//    private String port;
//
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        return new JavaMailSenderImpl();
//    }
//
//    @Bean
//    public MailSender mailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(host);
//        mailSender.setPort(Integer.parseInt(port));
//        mailSender.setUsername(from);
//        mailSender.setPassword(password);
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable",true);
//        props.put("mail.smtp.auth",true);
//        props.put("mail.transport.protocol","smtp");
//        props.put("mail.debug",true);
//        props.put("mail.smtp.ssl.trust",true);
//        props.put("mail.mime.address.strict", "false");
//        Session session = Session.getDefaultInstance(props);
//        mailSender.setJavaMailProperties(props);
//        return mailSender;
//    }
//}
