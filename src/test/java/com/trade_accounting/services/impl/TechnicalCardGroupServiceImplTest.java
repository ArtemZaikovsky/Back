package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalCardGroup;
import com.trade_accounting.models.dto.TechnicalCardGroupDto;
import com.trade_accounting.repositories.TechnicalCardGroupRepository;
import com.trade_accounting.services.impl.Stubs.dto.TechnicalCardGroupDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.TechnicalCardGroupModelStubs;
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
public class TechnicalCardGroupServiceImplTest {

    @Mock
    private TechnicalCardGroupRepository technicalCardGroupRepository;

    @InjectMocks
    private TechnicalCardGroupServiceImpl technicalCardGroupService;

    @Test
    void getAll(){
        when(technicalCardGroupRepository.findAll())
                .thenReturn(List.of(
                        TechnicalCardGroupModelStubs.getTechnicalCardGroup(1L),
                        TechnicalCardGroupModelStubs.getTechnicalCardGroup(2L),
                        TechnicalCardGroupModelStubs.getTechnicalCardGroup(3L)
                ));
        List<TechnicalCardGroupDto> technicalCardGroupDtos = technicalCardGroupService.getAll();

        assertEquals(3,technicalCardGroupDtos.size());
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
    void getById() {
        when(technicalCardGroupRepository.getOne(anyLong()))
                .thenReturn(TechnicalCardGroupModelStubs.getTechnicalCardGroup(1L));

        TechnicalCardGroupDto technicalCardGroupDto = technicalCardGroupService.getById(1L);

        assertEquals(1, technicalCardGroupDto.getId());
    }

    @Test
    void deleteById() {
        technicalCardGroupService.deleteById(anyLong());
        verify(technicalCardGroupRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(technicalCardGroupRepository.save(any(TechnicalCardGroup.class)))
                .thenReturn(TechnicalCardGroupModelStubs.getTechnicalCardGroup(1L));

        TechnicalCardGroupDto technicalCardGroupDto = technicalCardGroupService
                .create(TechnicalCardGroupDtoStubs.getDto(1L));

        assertEquals(1, technicalCardGroupDto.getId());
        verify(technicalCardGroupRepository).save(any(TechnicalCardGroup.class));
    }
}
