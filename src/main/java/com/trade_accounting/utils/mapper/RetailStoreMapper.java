package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailStoreDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RetailStoreMapper {
    /**
     * @return RetailStore
     */
    default RetailStore toModel(RetailStoreDto retailStoreDto) {
        if (retailStoreDto == null) {
            return null;
        }

        return RetailStore.builder()
                .id(retailStoreDto.getId())
                .activityStatus(retailStoreDto.getActivityStatus())
                .defaultTaxationSystem(retailStoreDto.getDefaultTaxationSystem())
                .isActive(retailStoreDto.getIsActive())
                .name(retailStoreDto.getName())
                .orderTaxationSystem(retailStoreDto.getOrderTaxationSystem())
                .revenue(retailStoreDto.getRevenue())
                .salesInvoicePrefix(retailStoreDto.getSalesInvoicePrefix())
                .build();
    }

    /**
     * @return RetailStoreDto
     */
    default RetailStoreDto toDto(RetailStore retailStore) {
        RetailStoreDto retailStoreDto = new RetailStoreDto();
        if (retailStore == null) {
            return null;
        } else {
            retailStoreDto.setId(retailStore.getId());
            retailStoreDto.setName(retailStore.getName());
            retailStoreDto.setIsActive(retailStore.getIsActive());
            retailStoreDto.setActivityStatus(retailStore.getActivityStatus());
            retailStoreDto.setRevenue(retailStore.getRevenue());
            retailStoreDto.setSalesInvoicePrefix(retailStore.getSalesInvoicePrefix());
            retailStoreDto.setDefaultTaxationSystem(retailStore.getDefaultTaxationSystem());
            retailStoreDto.setOrderTaxationSystem(retailStore.getOrderTaxationSystem());

            Company company = retailStore.getCompany();
            if (company == null) {
                return null;
            } else {
                retailStoreDto.setCompanyId(company.getId());

                List<Long> retailStoreIds = retailStore.getCashiers().stream()
                        .map(Employee::getId)
                        .collect(Collectors.toList());
                retailStoreDto.setCashiersIds(retailStoreIds);

                return retailStoreDto;
            }
        }
    }
}