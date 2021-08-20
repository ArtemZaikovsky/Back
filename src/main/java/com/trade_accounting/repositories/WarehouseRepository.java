package com.trade_accounting.repositories;

import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.WarehouseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("select new com.trade_accounting.models.dto.WarehouseDto(e.id, e.name, e.sortNumber, e.address, e.commentToAddress, e.comment) from Warehouse e")
    List<WarehouseDto> getAll();

    @Query("select new com.trade_accounting.models.dto.WarehouseDto(e.id, e.name, e.sortNumber, e.address, e.commentToAddress, e.comment) from Warehouse e where e.id = :id")
    WarehouseDto getById(@Param("id") Long id);

}
