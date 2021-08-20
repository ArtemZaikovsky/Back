package com.trade_accounting.services.impl;

import com.trade_accounting.models.Address;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.repositories.AccessParametersRepository;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.ContactRepository;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.ContractorStatusRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.ContractorService;
import com.trade_accounting.utils.mapper.ContractorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractorServiceImpl implements ContractorService {

    private final ContractorRepository contractorRepository;
    private final ContractorGroupRepository contractorGroupRepository;
    private final TypeOfPriceRepository typeOfPriceRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final AccessParametersRepository accessParametersRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ContractorMapper contractorMapper;
    private final ContractorStatusRepository contractorStatusRepository;


    @Override
    public List<ContractorDto> search(Specification<Contractor> specification) {
        return executeSearch(contractorRepository, contractorMapper::contractorToContractorDto, specification);
    }

    @Override
    public List<ContractorDto> getAll() {
        List<Contractor> list = contractorRepository.findAll();
        return list.stream()
                .map(contractorMapper::contractorToContractorDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<ContractorDto> getAll(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<Contractor> all = contractorRepository.findAll();
            return all.stream().map(contractorMapper::contractorToContractorDto).collect(Collectors.toList());
        } else {
            List<Contractor> list = contractorRepository.search(searchTerm);
            return list.stream().map(contractorMapper::contractorToContractorDto).collect(Collectors.toList());
        }
    }

    @Override
    public ContractorDto getById(Long id) {
        return contractorMapper.contractorToContractorDto(
                contractorRepository.findById(id).orElse(new Contractor())
        );
    }

    @Override
    public ContractorDto create(ContractorDto contractorDto) {
        Contractor contractor = contractorMapper.contractorDtoToContractor(contractorDto);

        contractor.setAddress(
                addressRepository.findById(
                        contractorDto.getAddressId()
                ).orElse(null)
        );

        contractor.setContact(
                contractorDto.getContactIds().stream()
                        .map(
                                contactRepository::getOne
                        )
                        .collect(Collectors.toList())
        );

        contractor.setBankAccounts(
                contractorDto.getBankAccountIds().stream()
                        .map(
                                bankAccountRepository::getOne
                        )
                        .collect(Collectors.toList())
        );

        contractor.setContractorGroup(
                contractorGroupRepository.findById(
                        contractorDto.getContractorGroupId()
                ).orElse(null)
        );

        contractor.setAccessParameters(
                accessParametersRepository.findById(
                        contractorDto.getAccessParametersId()
                ).orElse(null)
        );

        contractor.setTypeOfPrice(
                typeOfPriceRepository.findById(
                        contractorDto.getTypeOfPriceId()
                ).orElse(null)
        );

        contractor.setLegalDetail(
                legalDetailRepository.findById(
                        contractorDto.getLegalDetailId()
                ).orElse(null)
        );

        contractor.setContractorStatus(
                contractorStatusRepository.findById(
                        contractorDto.getContractorStatusId()
                ).orElse(null)
        );

        return contractorMapper.contractorToContractorDto(contractorRepository.save(contractor));
    }

    @Override
    public ContractorDto update(ContractorDto contractorDto) {
        Contractor contractor = contractorMapper.contractorDtoToContractor(contractorDto);

        Address address = addressRepository.getOne(contractorDto.getAddressId());
        addressRepository.save(address);
        contractor.setAddress(address);

        contractor.setContact(
                contractorDto.getContactIds().stream()
                        .map(
                                contactRepository::getOne
                        )
                        .collect(Collectors.toList())
        );

        contractor.setContractorGroup(
                contractorGroupRepository.findById(contractorDto.getContractorGroupId()).orElse(null));

        contractor.setTypeOfPrice(
                typeOfPriceRepository.findById(contractorDto.getTypeOfPriceId()).orElse(null));

        contractor.setLegalDetail(
                legalDetailRepository.findById(contractorDto.getLegalDetailId()).orElse(null));

        contractorRepository.save(contractor);

        return contractorDto;
    }

    @Override
    public void deleteById(Long id) {
        contractorRepository.deleteById(id);
    }
}
