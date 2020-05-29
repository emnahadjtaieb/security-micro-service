package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String email, String username, String password) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Bonjour,\n" +
                "Votre nom d'utilisateur et mot de passe sur notre application sont :\n" +
                "Nom d'utilisateur : "+username +"\n"+
                "Mot de passe : "+ password);

        javaMailSender.send(msg);

    }
}
