package com.trade_accounting.services.impl;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.repositories.CurrencyRepository;
import com.trade_accounting.services.interfaces.CurrencyService;
import com.trade_accounting.utils.mapper.CurrencyMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final CurrencyMapper currencyMapper;

    @Override
    public List<CurrencyDto> getAll() {
        return currencyRepository.getAll();
    }

    @Override
    public List<CurrencyDto> search(Specification<Currency> spec) {
        return executeSearch(currencyRepository, currencyMapper::toDto, spec);
    }

    @Override
    public CurrencyDto getById(Long id) {
        return currencyRepository.getById(id);
    }

    @Override
    public CurrencyDto create(CurrencyDto currencyDto) {
        Currency currency = currencyRepository.save(
                currencyMapper.toModel(currencyDto)
        );
        currencyDto.setId(currency.getId());
        return currencyMapper.toDto(currency);
    }

    @Override
    public CurrencyDto update(CurrencyDto currencyDto) {
        return create(currencyDto);
    }

    @Override
    public void deleteById(Long id) {
        currencyRepository.deleteById(id);
    }
}
