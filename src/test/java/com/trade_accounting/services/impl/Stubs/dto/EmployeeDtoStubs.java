package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.EmployeeMapper;
import org.mapstruct.factory.Mappers;

public class EmployeeDtoStubs {
    private static final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
    public static EmployeeDto getEmployeeDto(Long id) {
        return mapper.toDto(
                ModelStubs.getEmployee(id)
        );
    }
}
