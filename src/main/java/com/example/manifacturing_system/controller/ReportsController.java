package com.example.manifacturing_system.controller;

import com.example.manifacturing_system.model.ProductionEntry;
import com.example.manifacturing_system.service.ProductionService;
import com.example.manifacturing_system.dto.MachineReportDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin("*")
public class ReportsController {

    private final ProductionService service;

    public ReportsController(ProductionService service) {
        this.service = service;
    }

    // ===================== SUMMARY =====================
    @GetMapping("/summary")
    public Map<String, Object> summary(
            @RequestParam(required = false) String date
    ) {

        List<ProductionEntry> list;

        if (date == null || date.isEmpty()) {
            list = service.getAllEntries(); // ✅ ALL DATA
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

        double avgOEE = availability * quality * 100;

        Map<String, Object> map = new HashMap<>();
        map.put("totalProduction", good);
        map.put("avgOEE", Math.round(avgOEE * 100.0) / 100.0);
        map.put("totalDowntime", downtime);
        map.put("defectRate", Math.round(defectRate * 100.0) / 100.0);

        return map;
    }

    // ===================== MACHINE-WISE =====================
    @GetMapping("/machine")
    public List<MachineReportDTO> machineWise(
            @RequestParam(required = false) String date
    ) {

        List<ProductionEntry> list;

        if (date == null || date.isEmpty()) {
            list = service.getAllEntries(); // ✅ ALL DATA
        } else {
            LocalDate selectedDate = LocalDate.parse(date);
            list = service.getAllEntries()
                    .stream()
                    .filter(p -> selectedDate.equals(p.getProductionDate()))
                    .collect(Collectors.toList());
        }

        List<MachineReportDTO> result = new ArrayList<>();

        for (ProductionEntry p : list) {
            result.add(
                    new MachineReportDTO(
                            p.getMachine(),
                            p.getGoodQty(),
                            p.getDefectQty(),
                            p.getDowntime(),
                            (p.getNotes() == null || p.getNotes().isEmpty())
                                    ? "-"
                                    : p.getNotes(),
                            p.getProductionDate() // ✅ DATE INCLUDED
                    )
            );
        }

        return result;
    }
}
