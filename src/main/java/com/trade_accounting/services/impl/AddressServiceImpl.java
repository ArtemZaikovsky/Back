package com.trade_accounting.services.impl;

import com.trade_accounting.models.Address;
import com.trade_accounting.models.dto.AddressDto;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.services.interfaces.AddressService;
import com.trade_accounting.utils.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAll() {
        List<Address> all = addressRepository.findAll();
        return all.stream().map(addressMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AddressDto getById(Long id) {
        Address address = addressRepository.getOne(id);
        return addressMapper.toDto(address);
    }

    @Override
    public AddressDto create(AddressDto addressDto) {
        Address address = addressMapper.toModel(addressDto);
        Address addressSaved = addressRepository.save(address);
        addressDto.setId(addressSaved.getId());
        return addressDto;
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        Address address = addressMapper.toModel(addressDto);
        Address save = addressRepository.save(address);
        return addressMapper.toDto(save);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
