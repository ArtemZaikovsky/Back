package com.trade_accounting.repositories;

import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.dto.InvoiceProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long>, JpaSpecificationExecutor<InvoiceProduct> {

    @Query("select new com.trade_accounting.models.dto.InvoiceProductDto(" +
            "e.id," +
            "e.invoice.id," +
            "e.product.id," +
            "e.amount," +
            "e.price) from InvoiceProduct e")
    List<InvoiceProductDto> getAll();

    @Query("select new com.trade_accounting.models.dto.InvoiceProductDto(" +
            "e.id," +
            "e.invoice.id," +
            "e.product.id," +
            "e.amount," +
            "e.price) from InvoiceProduct e where e.id =:id")
    InvoiceProductDto getById(@Param("id") Long id);

//    @Query("select new com.trade_accounting.models.dto.InvoiceProductDto(" +
//            "e.id," +
//            "e.invoice.id," +
//            "e.product.id," +
//            "e.amount," +
//            "e.price) from InvoiceProduct e where e.invoice.id =:id")
//    List<InvoiceProductDto> getByInvoiceId(@Param("id") Long id);

    @Query("select ip from InvoiceProduct ip where ip.invoice.id =:id")
    List<InvoiceProduct> getByInvoiceId(@Param("id") Long id);

}
