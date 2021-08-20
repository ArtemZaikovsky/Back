package com.trade_accounting.repositories;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.dto.InvoiceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

    List<Invoice> findByTypeOfInvoice(TypeOfInvoice typeOfInvoice);

    @Query("select new com.trade_accounting.models.dto.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.comment) from Invoice e")
    List<InvoiceDto> getAll();

    @Query("select new com.trade_accounting.models.dto.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.comment) from Invoice e where lower(concat(e.id, e.comment)) " +
            "like concat('%', :search, '%') and e.typeOfInvoice = :typeOfInvoice")
    List<InvoiceDto> findBySearchAndTypeOfInvoice(@Param("search") String search,
                                                  @Param("typeOfInvoice") TypeOfInvoice typeOfInvoice);

    @Query("select new com.trade_accounting.models.dto.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.comment) from Invoice e where e.id = :id")
    InvoiceDto getById(@Param("id") Long id);

    @Query("SELECT new com.trade_accounting.models.dto.InvoiceDto(" +
            "e.id, " +
            "e.date, " +
            "e.typeOfInvoice, " +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.comment) from Invoice e " +
            "where lower(concat(e.id, e.comment)) like lower(concat('%', :query,'%')) and e.typeOfInvoice = :typeOfInvoice")
    List<InvoiceDto> search(@Param("query") String query, @Param("typeOfInvoice") TypeOfInvoice typeOfInvoice);
}
