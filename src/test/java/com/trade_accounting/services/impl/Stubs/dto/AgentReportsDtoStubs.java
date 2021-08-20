package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.services.impl.Stubs.model.AgentReportsModelStubs;
import com.trade_accounting.utils.mapper.AgentReportsMapper;
import org.mapstruct.factory.Mappers;

public class AgentReportsDtoStubs {
    private static final AgentReportsMapper mapper = Mappers.getMapper(AgentReportsMapper.class);

    public static AgentReportsDto getDto(Long id) {
        AgentReports model = AgentReportsModelStubs.getAgentReports(id);
        return mapper.toDto(model);
    }
}
