package com.example.manifacturing_system.controller;

import com.example.manifacturing_system.dto.ContactRequestDTO;
import com.example.manifacturing_system.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(
        origins = "http://localhost:5173",
        allowedHeaders = "*",
        methods = {RequestMethod.POST, RequestMethod.OPTIONS}
)
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody ContactRequestDTO dto) {
        contactService.sendContactEmail(dto);
        return ResponseEntity.ok("Message sent successfully");
    }
}
