package com.trade_accounting.services.impl;

import com.trade_accounting.models.AccessParameters;
import com.trade_accounting.models.dto.AccessParametersDto;
import com.trade_accounting.repositories.AccessParametersRepository;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.services.interfaces.AccessParametersService;
import com.trade_accounting.utils.mapper.AccessParametersMapper;
import com.trade_accounting.utils.mapper.DepartmentMapper;
import com.trade_accounting.utils.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccessParametersServiceImpl implements AccessParametersService {

    private final AccessParametersRepository accessParametersRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;
    private final AccessParametersMapper accessParametersMapper;

    @Override
    public List<AccessParametersDto> getAll() {
        return accessParametersRepository.findAll().stream()
                .map(accessParametersMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccessParametersDto getById(Long id) {
        return accessParametersMapper.toDto(accessParametersRepository.getOne(id));
    }

    @Override
    public AccessParametersDto create(AccessParametersDto dto) {
        return accessParametersMapper.toDto(accessParametersRepository
                .save(AccessParameters.builder().id(dto.getId()).generalAccess(dto.getGeneralAccess())
                        .employee(employeeMapper.toModel(employeeRepository.getById(dto.getEmployeeId())))
                        .department(departmentMapper.toModel(departmentRepository.getById(dto.getDepartmentId()))).build()));
    }

    @Override
    public AccessParametersDto update(AccessParametersDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        accessParametersRepository.deleteById(id);
    }
}
