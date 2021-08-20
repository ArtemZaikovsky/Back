package com.trade_accounting.repositories;

import com.trade_accounting.models.Inventarization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarizationRepository extends JpaRepository <Inventarization, Long>,
        JpaSpecificationExecutor<Inventarization> {
}
