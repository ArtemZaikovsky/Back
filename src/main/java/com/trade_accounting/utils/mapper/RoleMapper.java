package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    //Role
    RoleDto toDto(Role role);

    Role toModel(RoleDto role);
}
