package com.trade_accounting.repositories;

import com.trade_accounting.models.ContractorStatus;
import com.trade_accounting.models.dto.ContractorStatusDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorStatusRepository extends JpaRepository<ContractorStatus, Long> {

    @Query("select new com.trade_accounting.models.dto.ContractorStatusDto(" +
            "s.id, " +
            "s.name) from ContractorStatus s where s.id = :id")
    ContractorStatusDto getById(@Param("id") Long id);
}
