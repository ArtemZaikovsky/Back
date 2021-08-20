package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.dto.AcceptanceDto;

import java.util.List;

public interface AcceptanceService extends AbstractService<AcceptanceDto>, SearchableService<Acceptance, AcceptanceDto>{

        List<AcceptanceDto> searchByNumberAndComment (String searchTerm);
}
