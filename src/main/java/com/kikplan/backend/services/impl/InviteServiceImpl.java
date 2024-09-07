package com.kikplan.backend.services.impl;

import com.kikplan.backend.services.InviteService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class InviteServiceImpl implements InviteService {


    private final JavaMailSender mailSender;
    @Override
    public void invite(String email) {
        Path path = Paths.get("src/main/resources/templates/email-sender.html");
        try {
            String htmlContent = new String(Files.readAllBytes(path));
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Invite to our project");
            helper.setText(htmlContent, true);


            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
