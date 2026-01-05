package com.example.manifacturing_system.service;

import com.example.manifacturing_system.model.ProductionEntry;
import com.example.manifacturing_system.repository.ProductionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductionService {

    private final ProductionRepository repo;

    public ProductionService(ProductionRepository repo) {
        this.repo = repo;
    }

    // Save production entry
    public ProductionEntry saveEntry(ProductionEntry entry) {

        // ✅ If date not sent from frontend, set today's date
        if (entry.getProductionDate() == null) {
            entry.setProductionDate(LocalDate.now());
        }

        return repo.save(entry);
    }

    // Get all entries
    public List<ProductionEntry> getAllEntries() {
        return repo.findAll();
    }
}
