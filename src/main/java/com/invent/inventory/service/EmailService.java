package com.invent.inventory.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.invent.inventory.Iservice.IEmailService;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender mailSender;
    private final String recipient;

    public EmailService(JavaMailSender mailSender,
            @Value("${inventory.alert.recipient}") String recipient) {
        this.mailSender = mailSender;
        this.recipient = recipient;
    }

    @Override
    public void sendLowStockAlert(String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
