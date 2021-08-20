package com.trade_accounting.services.impl;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.repositories.AccessParametersRepository;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.ContactRepository;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.ContractorStatusRepository;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.ContractorDtoStubs;
import com.trade_accounting.utils.mapper.ContractorMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractorServiceTest {
    @Mock
    private ContractorRepository contractorRepository;
    @Mock
    private ContractorGroupRepository contractorGroupRepository;
    @Mock
    private TypeOfContractorRepository typeOfContractorRepository;
    @Mock
    private TypeOfPriceRepository typeOfPriceRepository;
    @Mock
    private LegalDetailRepository legalDetailRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private  AccessParametersRepository accessParametersRepository;
    @Mock
    private  EmployeeRepository employeeRepository;
    @Mock
    private  DepartmentRepository departmentRepository;
    @Mock
    private  BankAccountRepository bankAccountRepository;
    @Mock
    private  ContractorStatusRepository contractorStatusRepository;
    @Spy
    private ContractorMapper contractorMapper;
    @Spy
    private ContactRepository contactRepository;
    @InjectMocks
    private ContractorServiceImpl contractorService;

    @Test
    void getAll_shouldReturnListFilledContractorDto() {
        when(contractorRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getContractor(1L),
                                ModelStubs.getContractor(2L),
                                ModelStubs.getContractor(3L)
                        )
                                .collect(Collectors.toList())
                );

        List<ContractorDto> contractorDtoList = contractorService.getAll();
        verify(contractorRepository).findAll();
        assertNotNull(contractorDtoList, "failure - expected that a list of contractorDto not null");
        assertTrue(contractorDtoList.size() > 0, "failure - expected that a list of contractorDto grater than 0");

        for (ContractorDto contractorDto : contractorDtoList) {
            contractorDtoIsCorrectlyInvitedDtoId(contractorDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListContractorDto() {
        when(contractorRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<ContractorDto> contractorDtoList = contractorService.getAll();

        assertNotNull(contractorDtoList, "failure - expected that a list of contractorDto not null");
        assertEquals(0, contractorDtoList.size(), "failure - expected that size of list of contractorDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledContractorDto() {
        Optional<Contractor> contractorFromRepo = Optional.of(ModelStubs.getContractor(1L));

        when(contractorRepository.findById(anyLong()))
                .thenReturn(contractorFromRepo);

        ContractorDto contractorDto = contractorService.getById(1L);

        assertNotNull(contractorDto, "failure - expected that contractor not null");

        contractorDtoIsCorrectlyInvitedDtoId(contractorDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulContractorCreate() {
        contractorService.create(ContractorDtoStubs.getContractorDto(1L));

        verify(contractorRepository).save(any(Contractor.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulContractorUpdate() {
        contractorService.update(
                ContractorDtoStubs.getContractorDto(1L)
        );

        verify(contractorRepository).save(any(Contractor.class));
    }

    @Test
    void deleteById() {
        contractorService.deleteById(1L);
        verify(contractorRepository).deleteById(1L);
    }

    void contractorDtoIsCorrectlyInvitedDtoId(ContractorDto contractorDto) {
        assertNotNull(contractorDto, "Fail in passed contractorDto");
        assertNotNull(contractorDto.getId(), "Fail in field 'id' of contractorDto");
        assertNotNull(contractorDto.getName(), "Fail in field 'name' of contractorDto");
        assertNotNull(contractorDto.getSortNumber(), "Fail in field 'sortNumber' of contractorDto");
        assertNotNull(contractorDto.getPhone(), "Fail in field 'phone' of contractorDto");
        assertNotNull(contractorDto.getFax(), "Fail in field 'fax' of contractorDto");
        assertNotNull(contractorDto.getEmail(), "Fail in field 'email' of contractorDto");
        assertNotNull(contractorDto.getAddressId(), "Fail in field 'address' of contractorDto");
        assertNotNull(contractorDto.getCommentToAddress(), "Fail in field 'comment to address' of contractorDto");
        assertNotNull(contractorDto.getComment(), "Fail in field 'name' of contractorDto");
        assertNotNull(contractorDto.getContractorGroupId(), "Fail in field 'ContractorGroupDto' of contractorDto");
        assertNotNull(contractorDto.getContactIds(), "Fail in field 'ContactDto' of contractorDto");
        assertNotNull(contractorDto.getTypeOfPriceId(), "Fail in field 'TypeOfPriceDto' of contractorDto");
        assertNotNull(contractorDto.getLegalDetailId(), "Fail in field 'LegalDetailDto' of contractorDto");

    }


}

