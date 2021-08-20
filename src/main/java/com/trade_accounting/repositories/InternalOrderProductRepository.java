package com.trade_accounting.repositories;

import com.trade_accounting.models.InternalOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InternalOrderProductRepository extends JpaRepository<InternalOrderProduct, Long>,
        JpaSpecificationExecutor<InternalOrderProduct> {
}
