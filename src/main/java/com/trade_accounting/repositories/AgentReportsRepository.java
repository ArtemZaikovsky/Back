package com.trade_accounting.repositories;

import com.trade_accounting.models.AgentReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentReportsRepository extends JpaRepository<AgentReports, Long> {

}
