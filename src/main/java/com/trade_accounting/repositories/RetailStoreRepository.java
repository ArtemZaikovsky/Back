package com.trade_accounting.repositories;

import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailStoreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailStoreRepository extends JpaRepository<RetailStore, Long> {

}
