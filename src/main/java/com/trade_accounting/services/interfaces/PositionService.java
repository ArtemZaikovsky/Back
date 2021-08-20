package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.PositionDto;

public interface PositionService extends AbstractService<PositionDto> {

    PositionDto getByName(String name);
}
