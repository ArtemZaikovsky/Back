package com.trade_accounting.services.impl;

import com.trade_accounting.models.AccessParameters;
import com.trade_accounting.models.Address;
import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contact;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.ContractorStatus;
import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.dto.InvoiceDtoStubs;
import com.trade_accounting.utils.mapper.CompanyMapper;
import com.trade_accounting.utils.mapper.ContractorMapper;
import com.trade_accounting.utils.mapper.InvoiceMapper;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.trade_accounting.services.impl.Stubs.model.InvoiceModelStubs.getInvoice;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InvoiceServiceImpl.class})
@ExtendWith(MockitoExtension.class)

class InvoiceServiceImplTest {


    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private ContractorRepository contractorRepository;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Spy
    private ContractorMapper contractorMapper;
    @Spy
    private CompanyMapper companyMapper;

    @Spy
    private InvoiceMapper invoiceMapper;

    @Test
    public void testFindBySearchAndTypeOfInvoice() {
        ArrayList<InvoiceDto> invoiceDtoList = new ArrayList<InvoiceDto>();
        when(this.invoiceRepository.findBySearchAndTypeOfInvoice(anyString(), (TypeOfInvoice) any()))
                .thenReturn(invoiceDtoList);
        List<InvoiceDto> actualFindBySearchAndTypeOfInvoiceResult = this.invoiceService
                .findBySearchAndTypeOfInvoice("Search", TypeOfInvoice.RECEIPT);
        assertSame(invoiceDtoList, actualFindBySearchAndTypeOfInvoiceResult);
        assertTrue(actualFindBySearchAndTypeOfInvoiceResult.isEmpty());
        verify(this.invoiceRepository).findBySearchAndTypeOfInvoice(anyString(), (TypeOfInvoice) any());
        assertEquals(actualFindBySearchAndTypeOfInvoiceResult, this.invoiceService.getAll());
    }


    @Test
    void getAll_shouldReturnListFilledInvoiceDto() {
        when(invoiceRepository.findAll())
                .thenReturn(
                        Stream.of(
                                getInvoice(1L),
                                getInvoice(2L),
                                getInvoice(3L)
                        ).collect(Collectors.toList())
                );
        List<InvoiceDto> invoiceDtoList = invoiceService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceDto not null"
        );
        assertTrue(
                invoiceDtoList.size() > 0,
                "failure - expected that a list of invoiceDto grater than 0"
        );
        for (InvoiceDto invoiceDto : invoiceDtoList) {
            invoiceListDtoIsCorrectlyInited(invoiceDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListInvoiceDtoList() {
        when(invoiceRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<InvoiceDto> invoiceDtoList =
                invoiceService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceDto not null"
        );
        assertEquals(
                0, invoiceDtoList.size(),
                "failure - expected that size of list of invoiceDto equals 0"
        );
    }

    @Test
    void getById_shouldReturnFilledInvoiceDto() {
        Optional<Invoice> invoiceDtoFromRepo =
                Optional.of(
                        getInvoice(1L)
                );

        when(invoiceRepository.findById(anyLong()))
                .thenReturn(invoiceDtoFromRepo);

        InvoiceDto invoiceDto =
                invoiceService.getById(1L);

        assertNotNull(
                invoiceDto,
                "failure - expected that invoiceDto not null"
        );

        invoiceListDtoIsCorrectlyInited(invoiceDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        invoiceService.create(
                InvoiceDtoStubs.getInvoiceDto(1L)
        );

        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        invoiceService.update(
                InvoiceDtoStubs.getInvoiceDto(1L)
        );

        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void deleteById() {
        invoiceService.deleteById(1L);
        verify(invoiceRepository).deleteById(1L);
    }


    void invoiceListDtoIsCorrectlyInited(InvoiceDto invoiceDto) {
        assertNotNull(invoiceDto, "Fail in passed invoiceDto");
        assertNotNull(invoiceDto.getId(), "Fail in field 'id' of invoiceDto");
    }

}
