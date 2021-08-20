package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.RoleMapper;
import org.mapstruct.factory.Mappers;

public class RoleDtoStubs {
    private static final RoleMapper mapper = Mappers.getMapper(RoleMapper.class);
    public static RoleDto getRoleDto(Long id) {
        return mapper.toDto(
                ModelStubs.getRole(id)
        );
    }
}
