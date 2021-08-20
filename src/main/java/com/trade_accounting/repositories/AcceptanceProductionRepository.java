package com.trade_accounting.repositories;

import com.trade_accounting.models.AcceptanceProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcceptanceProductionRepository extends JpaRepository<AcceptanceProduction, Long>,
        JpaSpecificationExecutor<AcceptanceProduction> {
}
