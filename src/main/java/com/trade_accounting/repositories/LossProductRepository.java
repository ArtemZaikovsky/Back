package com.trade_accounting.repositories;

import com.trade_accounting.models.LossProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LossProductRepository extends JpaRepository<LossProduct,Long>, JpaSpecificationExecutor<LossProduct> {
}
