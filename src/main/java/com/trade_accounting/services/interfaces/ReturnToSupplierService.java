package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.ReturnToSupplier;
import com.trade_accounting.models.dto.ReturnToSupplierDto;

import java.util.List;

public interface ReturnToSupplierService extends AbstractService<ReturnToSupplierDto>,
        SearchableService<ReturnToSupplier, ReturnToSupplierDto> {

    List<ReturnToSupplierDto> searchByString(String nameFilter);

}
