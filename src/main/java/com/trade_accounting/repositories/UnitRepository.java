package com.trade_accounting.repositories;

import com.trade_accounting.models.Unit;
import com.trade_accounting.models.dto.UnitDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query("select new com.trade_accounting.models.dto.UnitDto(e.id, e.shortName, e.fullName, e.sortNumber) from Unit e")
    List<UnitDto> getAll();

    @Query("select new com.trade_accounting.models.dto.UnitDto(e.id, e.shortName, e.fullName, e.sortNumber) from Unit e where e.id = :id")
    UnitDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.UnitDto(p.unit.id, p.unit.shortName, p.unit.fullName, p.unit.sortNumber) from Product p where p.id = :id")
    UnitDto getUnitByProductId(@Param("id") Long id);
}
