package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.dto.TechnicalCardProductionDto;

import java.util.List;

public interface TechnicalCardProductionService extends AbstractService<TechnicalCardProductionDto>{
    List<TechnicalCardProduction> finaAllById(List<Long> id);
}
