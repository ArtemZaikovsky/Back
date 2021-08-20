package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ContactDto;
import com.trade_accounting.services.impl.Stubs.model.ContactModelStubs;
import com.trade_accounting.utils.mapper.ContactMapper;
import org.mapstruct.factory.Mappers;


public class ContactDtoStubs {
    private static final ContactMapper mapper = Mappers.getMapper(ContactMapper.class);

    public static ContactDto getDto(Long id) {
        return mapper.toDto(ContactModelStubs.getContact(id));
    }
}
