package com.example.manifacturing_system.service;

import com.example.manifacturing_system.dto.ContactRequestDTO;

public interface ContactService {
    void sendContactEmail(ContactRequestDTO dto);
}
