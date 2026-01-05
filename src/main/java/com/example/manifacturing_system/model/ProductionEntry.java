package com.example.manifacturing_system.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "production_entry")
public class ProductionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String workOrder;
    private String stage;
    private String machine;
    private int goodQty;
    private int defectQty;
    private int downtime;
    private String notes;

    // ✅ NEW FIELD: Production Date
    private LocalDate productionDate;

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public int getGoodQty() {
        return goodQty;
    }

    public void setGoodQty(int goodQty) {
        this.goodQty = goodQty;
    }

    public int getDefectQty() {
        return defectQty;
    }

    public void setDefectQty(int defectQty) {
        this.defectQty = defectQty;
    }

    public int getDowntime() {
        return downtime;
    }

    public void setDowntime(int downtime) {
        this.downtime = downtime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // ✅ NEW getter & setter
    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }
}
