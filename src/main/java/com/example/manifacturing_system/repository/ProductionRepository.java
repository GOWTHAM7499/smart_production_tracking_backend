package com.example.manifacturing_system.repository;

import com.example.manifacturing_system.model.ProductionEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProductionRepository extends JpaRepository<ProductionEntry, Long> {

    // =========================
    // EXISTING (NO CHANGE)
    // =========================

    // Total output
    @Query("SELECT COALESCE(SUM(p.goodQty),0) FROM ProductionEntry p")
    int totalOutput();

    // Total defects
    @Query("SELECT COALESCE(SUM(p.defectQty),0) FROM ProductionEntry p")
    int totalDefects();

    // Total downtime
    @Query("SELECT COALESCE(SUM(p.downtime),0) FROM ProductionEntry p")
    int totalDowntime();

    // Latest records for table
    List<ProductionEntry> findTop10ByOrderByIdDesc();


    // =========================
    // ✅ NEW: DATE-BASED QUERIES
    // =========================

    // All entries for a specific date
    List<ProductionEntry> findByProductionDate(LocalDate productionDate);

    // Entries between two dates (Reports)
    List<ProductionEntry> findByProductionDateBetween(LocalDate startDate, LocalDate endDate);

    // Total output by date
    @Query("""
        SELECT COALESCE(SUM(p.goodQty),0)
        FROM ProductionEntry p
        WHERE p.productionDate = :date
    """)
    int totalOutputByDate(LocalDate date);

    // Total defects by date
    @Query("""
        SELECT COALESCE(SUM(p.defectQty),0)
        FROM ProductionEntry p
        WHERE p.productionDate = :date
    """)
    int totalDefectsByDate(LocalDate date);

    // Total downtime by date
    @Query("""
        SELECT COALESCE(SUM(p.downtime),0)
        FROM ProductionEntry p
        WHERE p.productionDate = :date
    """)
    int totalDowntimeByDate(LocalDate date);
}
