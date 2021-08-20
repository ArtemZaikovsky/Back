package com.trade_accounting.repositories;

import com.trade_accounting.models.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Production, Long> {

}
