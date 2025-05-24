package com.silviaferrari.pbs.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(String to, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Benvenuto su Panettoni by Silvia!");
        message.setText("Ciao " + username + ",\n\nGrazie per esserti registrato sul nostro sito! " +
                "🎉\n\nPanettoni by Silvia \n\nQuesta è una mail automatica, non rispondere. Per necessità contattare l'assistenza clienti.");

        mailSender.send(message);
    }
}

