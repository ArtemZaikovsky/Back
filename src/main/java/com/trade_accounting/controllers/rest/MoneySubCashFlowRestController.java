package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.CorrectionDto;
import com.trade_accounting.models.dto.MoneySubCashFlowDto;
import com.trade_accounting.services.interfaces.MoneySubCashFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "MoneySubCashFlow Rest Controller", description = "Получение данных о всех транзакция")
@Api(tags = "MoneySubCashFlow Rest Controller")
@RequestMapping("/api/money")
public class MoneySubCashFlowRestController {

    private final MoneySubCashFlowService moneySubCashFlowService;

    public MoneySubCashFlowRestController(MoneySubCashFlowService moneySubCashFlowService) {
        this.moneySubCashFlowService = moneySubCashFlowService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех транзакций")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка транзакций"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<MoneySubCashFlowDto>> getAll() {
        return ResponseEntity.ok(moneySubCashFlowService.getAll());
    }
}
