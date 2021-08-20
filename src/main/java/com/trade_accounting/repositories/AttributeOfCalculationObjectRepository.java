package com.trade_accounting.repositories;

import com.trade_accounting.models.AttributeOfCalculationObject;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeOfCalculationObjectRepository extends JpaRepository<AttributeOfCalculationObject, Long> {

    @Query("select new com.trade_accounting.models.dto.AttributeOfCalculationObjectDto(" +
            "attribute.id, " +
            "attribute.name, " +
            "attribute.sortNumber, " +
            "attribute.isService" +
            ") " +
            "from AttributeOfCalculationObject attribute")
    List<AttributeOfCalculationObjectDto> getAll();

    @Query("select new com.trade_accounting.models.dto.AttributeOfCalculationObjectDto(" +
            "attribute.id, " +
            "attribute.name, " +
            "attribute.sortNumber, " +
            "attribute.isService" +
            ") " +
            "from AttributeOfCalculationObject attribute " +
            "where attribute.id = :id")
    AttributeOfCalculationObjectDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.AttributeOfCalculationObjectDto(" +
            "p.attributeOfCalculationObject.id, " +
            "p.attributeOfCalculationObject.name, " +
            "p.attributeOfCalculationObject.sortNumber, " +
            "p.attributeOfCalculationObject.isService" +
            ") " +
            "from Product p where p.id = :id")
    AttributeOfCalculationObjectDto getAttributeOfCalculationObjectById(@Param("id") Long id);
}
