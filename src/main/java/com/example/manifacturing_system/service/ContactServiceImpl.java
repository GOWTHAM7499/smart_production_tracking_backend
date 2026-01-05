package com.example.manifacturing_system.service;

import com.example.manifacturing_system.dto.ContactRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendContactEmail(ContactRequestDTO dto) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("gowtham.ec22@bitsathy.ac.in");
        mail.setSubject("New Contact Message");
        mail.setText(
                "Name: " + dto.getName() + "\n" +
                        "Email: " + dto.getEmail() + "\n\n" +
                        "Message:\n" + dto.getMessage()
        );

        mailSender.send(mail);
    }
}
