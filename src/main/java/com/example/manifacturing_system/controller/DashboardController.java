package com.example.manifacturing_system.controller;

import com.example.manifacturing_system.model.ProductionEntry;
import com.example.manifacturing_system.service.ProductionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final ProductionService service;

    public DashboardController(ProductionService service) {
        this.service = service;
    }

    // ================= KPI SUMMARY =================
    @GetMapping("/summary")
    public Map<String, Object> summary(
            @RequestParam(required = false) String date
    ) {

        List<ProductionEntry> list;

        if (date == null || date.isEmpty()) {
            // ✅ SHOW ALL DATA (NO DATE FILTER)
            list = service.getAllEntries();
        } else {
            LocalDate selectedDate = LocalDate.parse(date);
            list = service.getAllEntries()
                    .stream()
                    .filter(p -> selectedDate.equals(p.getProductionDate()))
                    .collect(Collectors.toList());
        }

        int good = list.stream().mapToInt(ProductionEntry::getGoodQty).sum();
        int defects = list.stream().mapToInt(ProductionEntry::getDefectQty).sum();
        int downtime = list.stream().mapToInt(ProductionEntry::getDowntime).sum();

        int totalProduced = good + defects;

        double defectRate = totalProduced == 0
                ? 0
                : (defects * 100.0 / totalProduced);

        int plannedTime = 480;

        double availability = plannedTime == 0
                ? 0
                : (plannedTime - downtime) / (double) plannedTime;

        double quality = totalProduced == 0
                ? 0
                : good / (double) totalProduced;

        double oee = availability * quality * 100;

        Map<String, Object> map = new HashMap<>();
        map.put("output", good);
        map.put("downtime", downtime);
        map.put("defectRate", Math.round(defectRate * 100.0) / 100.0);
        map.put("oee", Math.round(oee * 100.0) / 100.0);

        return map;
    }

    // ================= TABLE DATA =================
    @GetMapping("/active-stages")
    public List<ProductionEntry> table(
            @RequestParam(required = false) String date
    ) {

        if (date == null || date.isEmpty()) {
            // ✅ SHOW ALL ENTRIES
            return service.getAllEntries();
        }

        LocalDate selectedDate = LocalDate.parse(date);
        return service.getAllEntries()
                .stream()
                .filter(p -> selectedDate.equals(p.getProductionDate()))
                .collect(Collectors.toList());
    }
}
