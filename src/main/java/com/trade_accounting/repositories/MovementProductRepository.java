package com.trade_accounting.repositories;

import com.trade_accounting.models.MovementProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovementProductRepository extends JpaRepository<MovementProduct, Long>,
        JpaSpecificationExecutor<MovementProduct> {
}
