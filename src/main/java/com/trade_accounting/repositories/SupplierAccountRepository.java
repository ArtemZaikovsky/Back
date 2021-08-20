package com.trade_accounting.repositories;

import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.dto.SupplierAccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAccountRepository extends JpaRepository<SupplierAccount,Long>,
                                                      JpaSpecificationExecutor<SupplierAccount> {

    @Query("select new com.trade_accounting.models.dto.SupplierAccountDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.warehouse.id," +
            "e.contract.id," +
            "e.contractor.id," +
            "e.isSpend," +
            "e.comment) from SupplierAccount  e")
    List<SupplierAccountDto> getAll();


    @Query("select new com.trade_accounting.models.dto.SupplierAccountDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.warehouse.id," +
            "e.contract.id," +
            "e.contractor.id," +
            "e.isSpend," +
            "e.comment) from SupplierAccount  e where  e.id = :id")
    SupplierAccountDto getById(@Param("id") Long id);

    @Query(
            "select new com.trade_accounting.models.dto.SupplierAccountDto (" +
                    "e.id," +
                    "e.date," +
                    "e.company.id," +
                    "e.warehouse.id," +
                    "e.contract.id," +
                    "e.contractor.id," +
                    "e.isSpend," +
                    "e.comment) from SupplierAccount  e where  lower(e.comment) " +
                    "                                   like lower(concat('%', :nameFilter,'%'))"
    )
    List<SupplierAccountDto> searchByNameFilter(@Param("nameFilter") String nameFilter);

    @Query("select new com.trade_accounting.models.dto.SupplierAccountDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.warehouse.id," +
            "e.contract.id," +
            "e.contractor.id," +
            "e.isSpend," +
            "e.comment) from SupplierAccount  e where  e.id = :nameFilter "
    )
    List<SupplierAccountDto> searchById(@Param("nameFilter") Long nameFilter);
}
