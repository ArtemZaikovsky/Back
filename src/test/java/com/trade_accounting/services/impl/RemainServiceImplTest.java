package com.trade_accounting.services.impl;

import com.trade_accounting.models.Remain;
import com.trade_accounting.models.dto.RemainDto;
import com.trade_accounting.repositories.RemainRepository;
import com.trade_accounting.services.impl.Stubs.dto.RemainDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.RemainModelStubs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemainServiceImplTest {

    @Mock
    private RemainRepository remainRepository;

    @InjectMocks
    private RemainServiceImpl remainService;

    @Test
    void getAll(){
        when(remainRepository.findAll())
                .thenReturn(List.of(
                        RemainModelStubs.getRemain(1L),
                        RemainModelStubs.getRemain(2L),
                        RemainModelStubs.getRemain(3L)
                ));

        List<RemainDto> remainDtos = remainService.getAll();

        assertEquals(3,remainDtos.size());
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
    void getById(){
        when(remainRepository.getOne(anyLong()))
                .thenReturn(RemainModelStubs.getRemain(1L));

        RemainDto remainDto = remainService.getById(1L);

        assertEquals(1,remainDto.getId());
    }

    @Test
    void deleteById() {
        remainService.deleteById(anyLong());
        verify(remainRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(remainRepository.save(any(Remain.class)))
                .thenReturn(RemainModelStubs.getRemain(1L));

        RemainDto remainDto = remainService
                .create(RemainDtoStubs.getDto(1L));

        assertEquals(1, remainDto.getId());
        verify(remainRepository).save(any(Remain.class));
    }
}
