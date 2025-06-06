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

    public void sendPaymentReceipt(String to, String cardholderName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Ricevuta pagamento - Ordine completato");
        message.setText("Ciao " + cardholderName + ",\n\nGrazie per il tuo ordine! Il pagamento è stato ricevuto con successo.\n\nSaluti,\nTeam PBS");
        mailSender.send(message);
    }
}

