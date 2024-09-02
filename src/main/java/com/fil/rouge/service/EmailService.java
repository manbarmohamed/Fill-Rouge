//package com.fil.rouge.service;
//
//
//import com.fil.rouge.emuns.ApplicationStatus;
//import com.fil.rouge.model.Application;
//import io.swagger.v3.oas.annotations.servers.Server;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.util.regex.Pattern;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//
//    public void sendEmail(Application application) {
//        String cleanEmail = cleanEmail(application.getWorker().getUsername());
//        if(!isValidEmail(cleanEmail)) {
//            throw new RuntimeException("Invalid email address: " + cleanEmail);
//        }
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(application.getWorker().getUsername());
//        message.setSubject("Status de votre application");
//        message.setText("Votre application pour task "+application.getTask().getTitle() +" a été " + (application.getStatus()== ApplicationStatus.ACCEPTED ? "accepted":"refusé"));
//        mailSender.send(message);
//
//    }
//
//    private String cleanEmail(String email) {
//        if (email == null) {
//            return "";
//        }
//        email = email.trim();
//        return email.replaceAll("[^a-zA-Z0-9@.+\\-_]", "");
//    }
//
//    private boolean isValidEmail(String email) {
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        if (email == null) {
//            return false;
//        }
//        return pattern.matcher(email).matches();
//    }
//}
