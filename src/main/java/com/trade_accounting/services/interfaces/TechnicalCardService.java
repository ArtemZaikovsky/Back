package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.dto.TechnicalCardDto;

import java.util.List;

public interface TechnicalCardService extends AbstractService<TechnicalCardDto>, SearchableService<TechnicalCard, TechnicalCardDto> {

    List<TechnicalCardDto> search (String searchTerm);

    List<TechnicalCardDto> getAllByTechnicalCardGroupId (Long id);
}
