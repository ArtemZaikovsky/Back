package com.trade_accounting.repositories;

import com.trade_accounting.models.PurchaseControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseControlRepository extends JpaRepository<PurchaseControl, Long>, JpaSpecificationExecutor<PurchaseControl> {
}
