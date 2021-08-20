package com.trade_accounting.repositories;

import com.trade_accounting.models.MutualSettlements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutualSettlementsRepository extends JpaRepository<MutualSettlements, Long> {
}
