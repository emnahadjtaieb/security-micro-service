package com.example.demo.common.email;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
