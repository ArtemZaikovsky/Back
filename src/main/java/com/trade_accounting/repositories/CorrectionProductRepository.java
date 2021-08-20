package com.trade_accounting.repositories;

import com.trade_accounting.models.CorrectionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectionProductRepository extends JpaRepository<CorrectionProduct, Long>,
        JpaSpecificationExecutor<CorrectionProduct> {
}
