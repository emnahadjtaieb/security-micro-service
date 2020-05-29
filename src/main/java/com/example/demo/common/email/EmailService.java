package com.example.demo.common;

import com.example.demo.entities.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    public void sendEmail(String email, String username, String password);
}
