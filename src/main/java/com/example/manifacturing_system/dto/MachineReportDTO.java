package com.example.manifacturing_system.dto;

import java.time.LocalDate;

public class MachineReportDTO {

    private String machine;
    private int goodQty;
    private int defectQty;
    private int downtime;
    private String defectReason;
    private LocalDate productionDate;   // ✅ NEW FIELD

    // ✅ UPDATED CONSTRUCTOR
    public MachineReportDTO(
            String machine,
            int goodQty,
            int defectQty,
            int downtime,
            String defectReason,
            LocalDate productionDate
    ) {
        this.machine = machine;
        this.goodQty = goodQty;
        this.defectQty = defectQty;
        this.downtime = downtime;
        this.defectReason = defectReason;
        this.productionDate = productionDate;
    }

    public String getMachine() {
        return machine;
    }

    public int getGoodQty() {
        return goodQty;
    }

    public int getDefectQty() {
        return defectQty;
    }

    public int getDowntime() {
        return downtime;
    }

    public String getDefectReason() {
        return defectReason;
    }

    public LocalDate getProductionDate() {   // ✅ GETTER
        return productionDate;
    }
}
