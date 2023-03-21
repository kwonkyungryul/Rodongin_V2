package shop.mtcoding.rodongin.util;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendMail {

    @Autowired
    private JavaMailSender emailSender;

    private final String from = "kkr0787@gmail.com";

    @Async
    public CompletableFuture<Void> sendMail(String to, String subject, String text) {
        return CompletableFuture.runAsync(() -> {
            SimpleMailMessage message = new SimpleMailMessage(); 
            message.setFrom(from);
            message.setTo(to); 
            message.setSubject(subject);
            message.setText(text);
            try {
                emailSender.send(message);
                log.error("에러");
            } catch (Exception e) {
                log.error("에러", e);
            }
        });
    }
}