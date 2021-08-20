package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.PurchaseControl;
import com.trade_accounting.models.dto.PurchaseControlDto;
import com.trade_accounting.repositories.PurchaseControlRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.PurchaseControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Purchase Control Rest Controller", description = "CRUD операции с управлением закупками")
@Api(tags = "Purchase Control Rest Controller")
@RequestMapping("/api/purchasecontrol")
@RequiredArgsConstructor
public class PurchaseControlRestController {
    private final CheckEntityService checkEntityService;
    private final PurchaseControlService purchaseControlService;
    private final PurchaseControlRepository purchaseControlRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех закупок")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка закупок"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<List<PurchaseControlDto>> getAll() {
        return ResponseEntity.ok(purchaseControlService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение закупки по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение закупки по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PurchaseControlDto> getById(@ApiParam(name = "id", type = "Long",
    value = "Переданный в URL id, по которому необходимо найти закупку")
                                                      @PathVariable(name = "id") Long id) {

        checkEntityService.checkExists((JpaRepository) purchaseControlRepository, id);
        return ResponseEntity.ok(purchaseControlService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "update", notes = "Изменение информации о закупке")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о закупке обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о закупке обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PurchaseControlDto> update(@ApiParam(name = "purchaseControlDto",
            value = "DTO закупки, которую необходимо обновить")
                                                         @RequestBody PurchaseControlDto purchaseControlDto) {
        return ResponseEntity.ok(purchaseControlService.update(purchaseControlDto));
    }

    @DeleteMapping
    @ApiOperation(value = "deleteById", notes = "Удаление закупки по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Закупка удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PurchaseControlDto> deleteById(@ApiParam(name = "id", type = "Long",
    value = "Переданный id, по которому необходимо удалить закупку")
                                                             @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) purchaseControlRepository, id);
        purchaseControlService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
