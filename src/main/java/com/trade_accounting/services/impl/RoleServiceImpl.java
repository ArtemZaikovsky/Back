package com.trade_accounting.services.impl;

import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.RoleService;
import com.trade_accounting.utils.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto getById(Long id) {
        return roleMapper.toDto(
                roleRepository.findById(id).orElse(new Role())
        );
    }

    @Override
    public RoleDto getByName(String name) {
        return roleMapper.toDto(
                roleRepository.findByName(name).orElse(new Role())
        );
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        Role role = roleRepository.save(
                roleMapper.toModel(roleDto)
        );
        roleDto.setId(role.getId());
        return roleMapper.toDto(role);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        return create(roleDto);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
