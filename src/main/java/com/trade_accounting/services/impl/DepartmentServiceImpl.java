package com.trade_accounting.services.impl;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.services.interfaces.DepartmentService;
import com.trade_accounting.utils.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getById(Long id) {
        return departmentMapper.toDto(
                departmentRepository.findById(id).orElse(new Department())
        );
    }

    @Override
    public DepartmentDto getByName(String name) {
        return departmentMapper.toDto(
                departmentRepository.findByName(name).orElse(new Department())
        );
    }

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
        departmentDto.setId(departmentMapper.toModel(departmentDto).getId());
        return departmentMapper.toDto(
                departmentRepository.save(departmentMapper.toModel(departmentDto))
        );

    }


    @Override
    public DepartmentDto update(DepartmentDto departmentDto) {
        return create(departmentDto);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
