package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.RequestsProductionsDto;
import com.trade_accounting.services.impl.Stubs.model.RequestsProductionsModelStubs;
import com.trade_accounting.utils.mapper.RequestsProductionsMapper;
import org.mapstruct.factory.Mappers;

public class RequestsProductionsDtoStubs {
    private static final RequestsProductionsMapper mapper = Mappers.getMapper(RequestsProductionsMapper.class);

    public static RequestsProductionsDto getDto(Long id) {
        return mapper.toDto(RequestsProductionsModelStubs.getRequestsProductions(id));
    }
}
