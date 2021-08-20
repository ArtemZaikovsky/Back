package com.trade_accounting.repositories;

import com.trade_accounting.models.Position;
import com.trade_accounting.models.dto.PositionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByName(String name);

    @Query("SELECT new com.trade_accounting.models.dto.PositionDto(" +
            "p.id, " +
            "p.name, " +
            "p.sortNumber" +
            ") " +
            "FROM Position p")
    List<PositionDto> getAll();

    @Query("SELECT new com.trade_accounting.models.dto.PositionDto(" +
            "p.id, " +
            "p.name, " +
            "p.sortNumber" +
            ") " +
            "FROM Position p " +
            "WHERE p.id = :id")
    PositionDto getById(@Param("id") Long id);

    @Query("SELECT new com.trade_accounting.models.dto.PositionDto(" +
            "p.id, " +
            "p.name, " +
            "p.sortNumber" +
            ") " +
            "FROM Position p " +
            "WHERE p.name = :name")
    PositionDto getByName(@Param("name") String name);

    @Query("select new com.trade_accounting.models.dto.PositionDto(" +
            "em.position.id, " +
            "em.position.name, " +
            "em.position.sortNumber" +
            ") " +
            "from Employee em " +
            "where em.id = :id")
    PositionDto getPositionByEmployeeId(@Param("id") Long id);
}



