package com.trade_accounting.repositories;

import com.trade_accounting.models.InternalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InternalOrderRepository extends JpaRepository<InternalOrder, Long>,
        JpaSpecificationExecutor<InternalOrder> {
}
