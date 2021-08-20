package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.dto.TechnicalCardDto;
import org.mapstruct.InheritInverseConfiguration;
import com.trade_accounting.models.TechnicalCard.TechnicalCardBuilder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TechnicalCardMapper {

    default TechnicalCardDto toDto(TechnicalCard technicalCard) {
        if (technicalCard == null) {
            return null;
        }

        TechnicalCardDto technicalCardDto = new TechnicalCardDto();
        List<Long> listFinalProduction = new ArrayList<>();
        List<Long> listMaterials = new ArrayList<>();

        for (TechnicalCardProduction technicalCardProduction : technicalCard.getFinalProduction()) {
            listFinalProduction.add(technicalCardProduction.getId());
            listMaterials.add(technicalCardProduction.getId());
        }

        technicalCardDto.setId(technicalCard.getId());
        technicalCardDto.setName(technicalCard.getName());
        technicalCardDto.setComment(technicalCard.getComment());
        technicalCardDto.setProductionCost(technicalCard.getProductionCost());
        technicalCardDto.setTechnicalCardGroupId(technicalCard.getTechnicalCardGroup().getId());
        technicalCardDto.setFinalProductionId(listFinalProduction);
        technicalCardDto.setMaterialsId(listMaterials);

        return technicalCardDto;
    }

//    @Mappings({
//            @Mapping(target = "technicalCardGroup", ignore = true),
//            @Mapping(target = "finalProduction", ignore = true),
//            @Mapping(target = "materials", ignore = true),
//    })
    default TechnicalCard toModel(TechnicalCardDto technicalCardDto) {
        if (technicalCardDto == null) {
            return null;
        }

        TechnicalCardBuilder technicalCard = TechnicalCard.builder();

        technicalCard.id( technicalCardDto.getId() );
        technicalCard.name( technicalCardDto.getName() );
        technicalCard.comment( technicalCardDto.getComment() );
        technicalCard.productionCost( technicalCardDto.getProductionCost() );

        return technicalCard.build();
    }

}
