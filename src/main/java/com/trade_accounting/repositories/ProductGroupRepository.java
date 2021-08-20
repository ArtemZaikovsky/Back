package com.trade_accounting.repositories;

import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.models.dto.UnitDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

    @Query("select new com.trade_accounting.models.dto.ProductGroupDto(" +
            "pg.id, " +
            "pg.name, " +
            "pg.sortNumber," +
            "pg.productGroup.id) from ProductGroup  pg where pg.serviceGroup = false")
    List<ProductGroupDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ProductGroupDto(" +
            "pg.id, " +
            "pg.name, " +
            "pg.sortNumber," +
            "pg.productGroup.id) from ProductGroup  pg where pg.serviceGroup = true")
    List<ProductGroupDto> getAllServices();

    @Query("select new com.trade_accounting.models.dto.ProductGroupDto(" +
            "pg.id, " +
            "pg.name, " +
            "pg.sortNumber," +
            "pg.productGroup.id) from ProductGroup  pg where pg.id = :id and pg.serviceGroup = false")
    ProductGroupDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ProductGroupDto(" +
            "pg.id, " +
            "pg.name, " +
            "pg.sortNumber," +
            "pg.productGroup.id) from ProductGroup  pg where pg.id = :id and pg.serviceGroup = true")
    ProductGroupDto getServicesById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ProductGroupDto(p.productGroup.id,  p.productGroup.name, p.productGroup.sortNumber, p.productGroup.productGroup.id) from Product p where p.id = :id and p.service = false")
    ProductGroupDto getProductGroupByProductId(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ProductGroupDto(p.productGroup.id,  p.productGroup.name, p.productGroup.sortNumber, p.productGroup.productGroup.id) from Product p where p.id = :id and p.service = true")
    ProductGroupDto getServiceGroupByProductId(@Param("id") Long id);
}
