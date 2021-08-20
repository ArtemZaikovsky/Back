package com.trade_accounting.repositories;

import com.trade_accounting.models.Loss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LossRepository extends JpaRepository<Loss,Long>, JpaSpecificationExecutor<Loss> {
}
