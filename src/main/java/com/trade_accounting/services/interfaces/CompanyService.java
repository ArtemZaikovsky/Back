package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;

public interface CompanyService extends AbstractService<CompanyDto>, SearchableService<Company, CompanyDto> {

    CompanyDto getByEmail(String email);
}
