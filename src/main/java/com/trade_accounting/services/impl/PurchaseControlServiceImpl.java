package com.trade_accounting.services.impl;

import com.trade_accounting.models.PurchaseControl;
import com.trade_accounting.models.dto.PurchaseControlDto;
import com.trade_accounting.repositories.PurchaseControlRepository;
import com.trade_accounting.services.interfaces.PurchaseControlService;
import com.trade_accounting.utils.mapper.PurchaseControlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseControlServiceImpl implements PurchaseControlService {
    private final PurchaseControlRepository purchaseControlRepository;
    private final PurchaseControlMapper purchaseControlMapper;


    @Override
    public List<PurchaseControlDto> getAll() {
        return purchaseControlRepository.findAll().stream()
                .map(purchaseControlMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseControlDto getById(Long id) {
        return purchaseControlMapper.toDto(purchaseControlRepository.getOne(id));
    }

    @Override
    public PurchaseControlDto create(PurchaseControlDto dto) {
        PurchaseControl purchaseControl = purchaseControlMapper.toModel(dto);
        purchaseControlRepository.save(purchaseControl);
        return dto;
    }

    @Override
    public PurchaseControlDto update(PurchaseControlDto dto) {
        PurchaseControl purchaseControl = purchaseControlMapper.toModel(dto);
        purchaseControlRepository.save(purchaseControl);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        purchaseControlRepository.deleteById(id);
    }
}
