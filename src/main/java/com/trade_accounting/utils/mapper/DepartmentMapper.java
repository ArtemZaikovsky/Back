package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    //Department
    DepartmentDto toDto(Department department);

    Department toModel(DepartmentDto department);
}
