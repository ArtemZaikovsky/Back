package com.trade_accounting.repositories;

import com.trade_accounting.models.InventarizationProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarizationProductRepository extends JpaRepository<InventarizationProduct, Long>,
        JpaSpecificationExecutor<InventarizationProduct> {
}
