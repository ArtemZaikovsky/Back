package com.trade_accounting.services.impl;

import com.trade_accounting.models.RequestsProductions;
import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.RequestsProductionsDto;
import com.trade_accounting.repositories.RequestsProductionsRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.RequestsProductionsService;
import com.trade_accounting.utils.mapper.RequestsProductionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestsProductionsServiceImpl implements RequestsProductionsService {

    private final TechnicalCardRepository technicalCardRepository;
    private final WarehouseRepository warehouseRepository;
    private final RequestsProductionsRepository requestsProductionsRepository;

    private final RequestsProductionsMapper requestsProductionsMapper;

    @Override
    public List<RequestsProductionsDto> getAll() {
        return requestsProductionsRepository.findAll().stream()
                .map(requestsProductionsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestsProductionsDto getById(Long id) {
        return requestsProductionsMapper.toDto(requestsProductionsRepository.getOne(id));
    }

    @Override
    public RequestsProductionsDto create(RequestsProductionsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public RequestsProductionsDto update(RequestsProductionsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        requestsProductionsRepository.deleteById(id);
    }

    public RequestsProductionsDto saveOrUpdate(RequestsProductionsDto dto) {
        RequestsProductions requestsProductions = requestsProductionsMapper.toModel(dto);
        TechnicalCard technicalCard = technicalCardRepository.getOne(dto.getTechnicalCardId());
        Warehouse warehouse = warehouseRepository.getOne(dto.getWarehouseId());
        LocalDate dateOfTheCertificate = LocalDate.parse(dto.getDateOfTheCertificate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        requestsProductions.setDateOfTheCertificate(dateOfTheCertificate);
        requestsProductions.setTechnicalCard(technicalCard);
        requestsProductions.setWarehouse(warehouse);
        return requestsProductionsMapper.toDto(requestsProductionsRepository.save(requestsProductions));
    }
}
