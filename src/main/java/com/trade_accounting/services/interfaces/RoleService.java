package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.RoleDto;

public interface RoleService extends AbstractService<RoleDto> {

    RoleDto getByName(String name);
}
