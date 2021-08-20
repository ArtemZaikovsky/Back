package com.trade_accounting.services.impl;

import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.PurchaseControl;
import com.trade_accounting.models.PurchaseCurrentBalance;
import com.trade_accounting.models.PurchaseForecast;
import com.trade_accounting.models.PurchaseHistoryOfSales;
import com.trade_accounting.models.dto.PurchaseControlDto;
import com.trade_accounting.repositories.PurchaseControlRepository;
import com.trade_accounting.services.impl.Stubs.dto.PurchaseControlDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.PurchaseControlModelStubs;
import com.trade_accounting.utils.mapper.PurchaseControlMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.metamodel.StaticMetamodel;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseControlServiceImplTest {

    @InjectMocks
    private PurchaseControlServiceImpl purchaseControlService;

    @Mock
    private PurchaseControlRepository purchaseControlRepository;

    @Mock
    private PurchaseHistoryOfSales purchaseHistoryOfSales;

    @Mock
    private PurchaseCurrentBalance purchaseCurrentBalance;

    @Mock
    private PurchaseForecast purchaseForecast;

    @Mock
    private ProductPrice productPrice;

    @Spy
    private PurchaseControlMapper purchaseControlMapper;

    @Test
    void getAll() {
        when(purchaseControlRepository.findAll())
                .thenReturn(
                        List.of(
                                PurchaseControlModelStubs.getPurchaseControl(1L),
                                PurchaseControlModelStubs.getPurchaseControl(2L),
                                PurchaseControlModelStubs.getPurchaseControl(3L)
                        )
                );
        List<PurchaseControlDto> purchaseControlDtos = purchaseControlService.getAll();

        assertEquals(3, purchaseControlDtos.size());
    }

    @Test
    void getById() {
        PurchaseControl test = PurchaseControlModelStubs.getPurchaseControl(1L);

        when(purchaseControlRepository.getOne(anyLong()))
                .thenReturn(PurchaseControlModelStubs.getPurchaseControl(1L));

        PurchaseControlDto purchaseControlDto = purchaseControlService.getById(1L);

        assertEquals(1, purchaseControlDto.getId());
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
        purchaseControlService.deleteById(anyLong());
        verify(purchaseControlRepository).deleteById(anyLong());
    }

    public void saveOrUpdate() {
        when(purchaseControlRepository.save(any(PurchaseControl.class)))
                .thenReturn(PurchaseControlModelStubs.getPurchaseControl(1L));

        PurchaseControlDto purchaseControlDto = purchaseControlService
                .create(PurchaseControlDtoStubs.getDto(1L));

        assertEquals(1, purchaseControlDto.getId());
        verify(purchaseControlRepository).save(any(PurchaseControl.class));
    }
}