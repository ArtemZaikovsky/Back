package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.CompanyMapper;
import org.mapstruct.factory.Mappers;

public class CompanyDtoStubs {
    private static final CompanyMapper mapper = Mappers.getMapper(CompanyMapper.class);
    public static CompanyDto getCompanyDto(Long id) {
        return mapper.toDto(
                ModelStubs.getCompany(id)
        );
    }
}
