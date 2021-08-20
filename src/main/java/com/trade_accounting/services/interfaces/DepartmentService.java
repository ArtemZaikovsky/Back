package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.DepartmentDto;

public interface DepartmentService extends AbstractService<DepartmentDto> {

    DepartmentDto getByName(String name);

}
