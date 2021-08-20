package com.trade_accounting.repositories;

import com.trade_accounting.models.Acceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcceptanceRepository extends JpaRepository<Acceptance, Long>, JpaSpecificationExecutor<Acceptance> {
    @Query("from Acceptance a" +
            " where lower(concat(a.incomingNumber, ' ', a.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<Acceptance> search(@Param("req") String request);
}
