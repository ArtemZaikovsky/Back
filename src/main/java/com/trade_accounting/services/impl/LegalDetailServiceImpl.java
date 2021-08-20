package com.trade_accounting.services.impl;

import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.LegalDetailService;
import com.trade_accounting.utils.mapper.LegalDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LegalDetailServiceImpl implements LegalDetailService {

    private final LegalDetailRepository legalDetailRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final AddressRepository addressRepository;
    private final LegalDetailMapper legalDetailMapper;

    @Override
    public List<LegalDetailDto> getAll() {
        return legalDetailRepository.findAll().stream()
                .map(legalDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LegalDetailDto getById(Long id) {
        return legalDetailMapper.toDto(
                legalDetailRepository.findById(id).orElse(new LegalDetail())
        );
    }

    @Override
    public LegalDetailDto create(LegalDetailDto legalDetailDto) {
        LegalDetail legalDetail = legalDetailMapper.toModel(legalDetailDto);

        legalDetail.setAddress(addressRepository.getOne(
                legalDetailDto.getAddressDtoId()
        ));

//        legalDetail.setTypeOfContractor(
//                typeOfContractorRepository.findByName(
//                        legalDetailDto.getTypeOfContractorDto()
//                ).orElse(null)
//        );

        return legalDetailMapper.toDto(
                legalDetailRepository.save(legalDetail)
        );
    }


    @Override
    public LegalDetailDto update(LegalDetailDto legalDetailDto) {
        LegalDetail legalDetail = legalDetailMapper.toModel(legalDetailDto);

        legalDetail.setAddress(addressRepository.getOne(legalDetailDto.getAddressDtoId()));

//        legalDetail.setTypeOfContractor(
//                typeOfContractorRepository.findByName(
//                        legalDetailDto.getTypeOfContractorDto()
//                ).orElse(null)
//        );

        return legalDetailMapper.toDto(
                legalDetailRepository.save(legalDetail)
        );
    }

    @Override
    public void deleteById(Long id) {
        legalDetailRepository.deleteById(id);
    }

}
