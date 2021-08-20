package com.trade_accounting.repositories;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long>, JpaSpecificationExecutor<Currency> {
    @Query("select new com.trade_accounting.models.dto.CurrencyDto(" +
            "currency.id, " +
            "currency.shortName, " +
            "currency.fullName," +
            "currency.digitalCode, " +
            "currency.letterCode, " +
            "currency.sortNumber) from Currency currency")
    List<CurrencyDto> getAll();

    @Query("select new com.trade_accounting.models.dto.CurrencyDto(" +
            "currency.id, " +
            "currency.shortName, " +
            "currency.fullName," +
            "currency.digitalCode, " +
            "currency.letterCode, " +
            "currency.sortNumber) from Currency currency " +
            "where currency.id = :id")
    CurrencyDto getById(@Param("id") Long id);
}
