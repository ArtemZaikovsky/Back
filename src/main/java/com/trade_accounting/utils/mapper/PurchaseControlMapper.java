package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.PurchaseControl;
import com.trade_accounting.models.PurchaseCurrentBalance;
import com.trade_accounting.models.PurchaseForecast;
import com.trade_accounting.models.PurchaseHistoryOfSales;
import com.trade_accounting.models.dto.PurchaseControlDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseControlMapper {
    /**
     * @return PurchaseControl
     */
//    @Mappings({
//            @Mapping(source = "historyOfSalesId", target = "historyOfSales.id"),
//            @Mapping(source = "currentBalanceId", target = "currentBalance.id"),
//            @Mapping(source = "forecastId", target = "forecast.id")
//    })
    default PurchaseControl toModel(PurchaseControlDto purchaseControlDto) {
        if ( purchaseControlDto == null ) {
            return null;
        }

        PurchaseControl.PurchaseControlBuilder purchaseControl = PurchaseControl.builder();

        purchaseControl.historyOfSales( purchaseControlDtoToPurchaseHistoryOfSales( purchaseControlDto ) );
        purchaseControl.currentBalance( purchaseControlDtoToPurchaseCurrentBalance( purchaseControlDto ) );
        purchaseControl.forecast( purchaseControlDtoToPurchaseForecast( purchaseControlDto ) );
        purchaseControl.id( purchaseControlDto.getId() );
        purchaseControl.productName( purchaseControlDto.getProductName() );
        purchaseControl.productCode( purchaseControlDto.getProductCode() );
        purchaseControl.articleNumber( purchaseControlDto.getArticleNumber() );
        purchaseControl.productMeasure( purchaseControlDto.getProductMeasure() );
        purchaseControl.productQuantity( purchaseControlDto.getProductQuantity() );

        return purchaseControl.build();
    }

    private PurchaseHistoryOfSales purchaseControlDtoToPurchaseHistoryOfSales(PurchaseControlDto purchaseControlDto) {
        if ( purchaseControlDto == null ) {
            return null;
        }

        PurchaseHistoryOfSales.PurchaseHistoryOfSalesBuilder purchaseHistoryOfSales = PurchaseHistoryOfSales.builder();

        purchaseHistoryOfSales.id( purchaseControlDto.getHistoryOfSalesId() );

        return purchaseHistoryOfSales.build();
    }

    private PurchaseCurrentBalance purchaseControlDtoToPurchaseCurrentBalance(PurchaseControlDto purchaseControlDto) {
        if ( purchaseControlDto == null ) {
            return null;
        }

        PurchaseCurrentBalance.PurchaseCurrentBalanceBuilder purchaseCurrentBalance = PurchaseCurrentBalance.builder();

        purchaseCurrentBalance.id( purchaseControlDto.getCurrentBalanceId() );

        return purchaseCurrentBalance.build();
    }

    private PurchaseForecast purchaseControlDtoToPurchaseForecast(PurchaseControlDto purchaseControlDto) {
        if ( purchaseControlDto == null ) {
            return null;
        }

        PurchaseForecast.PurchaseForecastBuilder purchaseForecast = PurchaseForecast.builder();

        purchaseForecast.id( purchaseControlDto.getForecastId() );

        return purchaseForecast.build();
    }

    /**
     * @return PurchaseControlDto
     */
//    @Mappings({
//            @Mapping(source = "historyOfSales.id", target = "historyOfSalesId"),
//            @Mapping(source = "currentBalance.id", target = "currentBalanceId"),
//            @Mapping(source = "forecast.id", target = "forecastId")
//    })
    default PurchaseControlDto toDto(PurchaseControl purchaseControl) {
        if ( purchaseControl == null ) {
            return null;
        }

        PurchaseControlDto.PurchaseControlDtoBuilder purchaseControlDto = PurchaseControlDto.builder();

        purchaseControlDto.historyOfSalesId( purchaseControlHistoryOfSalesId( purchaseControl ) );
        purchaseControlDto.currentBalanceId( purchaseControlCurrentBalanceId( purchaseControl ) );
        purchaseControlDto.forecastId( purchaseControlForecastId( purchaseControl ) );
        purchaseControlDto.id( purchaseControl.getId() );
        purchaseControlDto.productName( purchaseControl.getProductName() );
        purchaseControlDto.productCode( purchaseControl.getProductCode() );
        purchaseControlDto.articleNumber( purchaseControl.getArticleNumber() );
        purchaseControlDto.productMeasure( purchaseControl.getProductMeasure() );
        purchaseControlDto.productQuantity( purchaseControl.getProductQuantity() );

        return purchaseControlDto.build();
    }

    private Long purchaseControlHistoryOfSalesId(PurchaseControl purchaseControl) {
        if ( purchaseControl == null ) {
            return null;
        }
        PurchaseHistoryOfSales historyOfSales = purchaseControl.getHistoryOfSales();
        if ( historyOfSales == null ) {
            return null;
        }
        Long id = historyOfSales.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long purchaseControlCurrentBalanceId(PurchaseControl purchaseControl) {
        if ( purchaseControl == null ) {
            return null;
        }
        PurchaseCurrentBalance currentBalance = purchaseControl.getCurrentBalance();
        if ( currentBalance == null ) {
            return null;
        }
        Long id = currentBalance.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long purchaseControlForecastId(PurchaseControl purchaseControl) {
        if ( purchaseControl == null ) {
            return null;
        }
        PurchaseForecast forecast = purchaseControl.getForecast();
        if ( forecast == null ) {
            return null;
        }
        Long id = forecast.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
