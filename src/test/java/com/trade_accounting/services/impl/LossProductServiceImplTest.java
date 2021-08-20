package com.trade_accounting.services.impl;

import com.trade_accounting.models.Loss;
import com.trade_accounting.models.LossProduct;
import com.trade_accounting.models.dto.LossDto;
import com.trade_accounting.models.dto.LossProductDto;
import com.trade_accounting.repositories.LossProductRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.impl.Stubs.dto.LossDtoStubs;
import com.trade_accounting.services.impl.Stubs.dto.LossProductDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.LossModelStubs;
import com.trade_accounting.services.impl.Stubs.model.LossProductModelStubs;
import com.trade_accounting.utils.mapper.LossProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LossProductServiceImplTest {
    @InjectMocks
    private LossProductServiceImpl lossProductService;

    @Mock
    private  LossProductRepository lossProductRepository;
    @Mock
    private  ProductRepository productRepository;
    @Spy
    private  LossProductMapper lossProductMapper;

    @Test
    void getAll() {
        when(lossProductRepository.findAll())
                .thenReturn(
                        List.of(
                                LossProductModelStubs.getLossProduct(1L),
                                LossProductModelStubs.getLossProduct(2L),
                                LossProductModelStubs.getLossProduct(3L)
                        ));

        List<LossProductDto> lossDtosDtos = lossProductService.getAll();

        assertEquals(3, lossDtosDtos.size());
    }

    @Test
    void getById() {
        when(lossProductRepository.getOne(anyLong()))
                .thenReturn(LossProductModelStubs.getLossProduct(1L));

        LossProductDto lossDtos = lossProductService.getById(1L);

        assertEquals(1, lossDtos.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        lossProductService.deleteById(anyLong());
        verify(lossProductRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(lossProductRepository.save(any(LossProduct.class)))
                .thenReturn(LossProductModelStubs.getLossProduct(1L));

        LossProductDto lossDto = lossProductService
                .create(LossProductDtoStubs.getDto(1L));

        assertEquals(1, lossDto.getId());
        verify(lossProductRepository).save(any(LossProduct.class));
    }
}


