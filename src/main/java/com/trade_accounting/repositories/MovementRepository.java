package com.trade_accounting.repositories;

import com.trade_accounting.models.Correction;
import com.trade_accounting.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long>,
        JpaSpecificationExecutor<Movement> {
    @Query("SELECT c FROM Movement c")
    List<Movement> getAll();

    @Query("SELECT c FROM Movement c WHERE c.id = :id")
    Movement getMovementById(@Param("id") Long id);
}

