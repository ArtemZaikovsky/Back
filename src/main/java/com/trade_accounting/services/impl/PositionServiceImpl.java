package com.trade_accounting.services.impl;


import com.trade_accounting.models.Position;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.services.interfaces.PositionService;
import com.trade_accounting.utils.mapper.PositionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    private final PositionMapper positionMapper;

    @Override
    public List<PositionDto> getAll() {
        return positionRepository.findAll().stream()
                .map(positionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PositionDto getById(Long id) {
        return positionMapper.toDto(
                positionRepository.findById(id).orElse(new Position())
        );
    }

    @Override
    public PositionDto getByName(String name) {
        return positionMapper.toDto(
                positionRepository.findByName(name).orElse(new Position())
        );
    }

    @Override
    public PositionDto create(PositionDto positionDto) {
        Position position = positionMapper.toModel(positionDto);
        positionDto.setId(position.getId());
        return positionMapper.toDto(
                positionRepository.save(position)
        );
    }


    @Override
    public PositionDto update(PositionDto positionDto) {
        return create(positionDto);
    }

    @Override
    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }
}
