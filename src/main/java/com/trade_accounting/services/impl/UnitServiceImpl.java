package com.trade_accounting.services.impl;

import com.trade_accounting.models.Unit;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.services.interfaces.UnitService;
import com.trade_accounting.utils.SortNumberConverter;
import com.trade_accounting.utils.mapper.UnitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    private final UnitMapper unitMapper;

    @Override
    public List<UnitDto> getAll() {
        return unitRepository.findAll().stream()
                .map(unitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UnitDto getById(Long id) {
        return unitMapper.toDto(
                unitRepository.findById(id).orElse(new Unit())
        );
    }

    @Override
    public UnitDto create(UnitDto unitDto) {
        Unit unit = unitMapper.toModel(unitDto);

        unit.setSortNumber(
                SortNumberConverter.convert(unitDto.getSortNumber())
        );
        unitDto.setId(unit.getId());
        unitRepository.save(unit);
        return unitDto;
    }

    @Override
    public UnitDto update(UnitDto unitDto) {
        create(unitDto);
        return unitDto;
    }

    @Override
    public void deleteById(Long id) {
        unitRepository.deleteById(id);
    }
}
