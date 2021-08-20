package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Pavel Andrusov
 * @version 1.0.0
 * */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternalOrderProductsDto {
    private Long id;

    private Long productId;

    private BigDecimal amount;

    private BigDecimal price;
}
