package com.example.manifacturing_system.controller;

import com.example.manifacturing_system.model.ProductionEntry;
import com.example.manifacturing_system.service.ProductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/production")
@CrossOrigin("*")
public class ProductionController {

    private final ProductionService service;

    public ProductionController(ProductionService service) {
        this.service = service;
    }

    // SAVE PRODUCTION ENTRY (WITH DATE SUPPORT)
    @PostMapping
    public ResponseEntity<ProductionEntry> save(@RequestBody ProductionEntry entry) {

        // productionDate will be automatically mapped
        // from frontend JSON (yyyy-MM-dd)

        ProductionEntry savedEntry = service.saveEntry(entry);
        return ResponseEntity.ok(savedEntry);
    }
}
