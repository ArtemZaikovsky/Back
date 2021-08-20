package com.trade_accounting.repositories;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Payout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayoutRepository extends JpaRepository<Payout, Long>, JpaSpecificationExecutor<Contractor> {

    @Query(
            "from payouts p LEFT OUTER JOIN RetailStore AS rs " +
                    "ON p.retailStore.id =  rs.id " +
                    "LEFT OUTER JOIN Company as com " +
                    "ON p.company.id =  com.id " +
                    " where p.whoWasPaid like concat('%', :searchTerm, '%') " +
                    "      or p.comment like concat('%', :searchTerm, '%')"
    )
    List<Payout> search(@Param("searchTerm") String searchTerm);
}
