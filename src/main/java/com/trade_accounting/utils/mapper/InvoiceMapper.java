package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.Movement;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.MovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    //Invoice
//    @Mappings({
//            @Mapping(source = "company.id", target = "companyId"),
//            @Mapping(source = "contractor.id", target = "contractorId"),
//            @Mapping(source = "warehouse.id", target = "warehouseId")
//    })
//    InvoiceDto toDto(Invoice invoice);
//
//    @Mappings({
//            @Mapping(source = "companyId", target = "company.id"),
//            @Mapping(source = "contractorId", target = "contractor.id"),
//            @Mapping(source = "warehouseId", target = "warehouse.id")
//    })
//    Invoice toModel(InvoiceDto emp);
//}

    default InvoiceDto toDto(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceDto.InvoiceDtoBuilder invoiceDto = InvoiceDto.builder();

        invoiceDto.companyId( invoiceCompanyId( invoice ) );
        invoiceDto.contractorId( invoiceContractorId( invoice ) );
        invoiceDto.warehouseId( invoiceWarehouseId( invoice ) );
        invoiceDto.id( invoice.getId() );
        invoiceDto.comment( invoice.getComment() );
        if ( invoice.getDate() != null ) {
            invoiceDto.date( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( invoice.getDate() ) );
        }
        if ( invoice.getTypeOfInvoice() != null ) {
            invoiceDto.typeOfInvoice( invoice.getTypeOfInvoice().name() );
        }
        invoiceDto.isSpend( invoice.getIsSpend() );

        return invoiceDto.build();
    }


    default Invoice toModel(InvoiceDto emp) {
        if ( emp == null ) {
            return null;
        }

        Invoice.InvoiceBuilder invoice = Invoice.builder();

        invoice.company( invoiceDtoToCompany( emp ) );
        invoice.contractor( invoiceDtoToContractor( emp ) );
        invoice.warehouse( invoiceDtoToWarehouse( emp ) );
        invoice.id( emp.getId() );
        if ( emp.getDate() != null ) {
            invoice.date( LocalDateTime.parse( emp.getDate() ) );
        }
        if ( emp.getTypeOfInvoice() != null ) {
            invoice.typeOfInvoice( Enum.valueOf( TypeOfInvoice.class, emp.getTypeOfInvoice() ) );
        }
        invoice.isSpend( emp.getIsSpend() );
        invoice.comment( emp.getComment() );

        return invoice.build();
    }

    private Long invoiceCompanyId(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Company company = invoice.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long invoiceContractorId(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Contractor contractor = invoice.getContractor();
        if ( contractor == null ) {
            return null;
        }
        Long id = contractor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long invoiceWarehouseId(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Warehouse warehouse = invoice.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        Long id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

      default Company invoiceDtoToCompany(InvoiceDto invoiceDto) {
        if ( invoiceDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( invoiceDto.getCompanyId() );

        return company;
    }

    default Contractor invoiceDtoToContractor(InvoiceDto invoiceDto) {
        if ( invoiceDto == null ) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId( invoiceDto.getContractorId() );

        return contractor;
    }

   default Warehouse invoiceDtoToWarehouse(InvoiceDto invoiceDto) {
        if ( invoiceDto == null ) {
            return null;
        }

        Warehouse.WarehouseBuilder warehouse = Warehouse.builder();

        warehouse.id( invoiceDto.getWarehouseId() );

        return warehouse.build();
    }
}
