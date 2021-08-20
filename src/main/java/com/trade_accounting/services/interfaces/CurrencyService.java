package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;

public interface CurrencyService extends AbstractService<CurrencyDto>, SearchableService<Currency, CurrencyDto> {

}
