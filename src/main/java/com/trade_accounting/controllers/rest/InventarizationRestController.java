package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.InventarizationDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.InventarizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Inventarization Rest Controller", description = "CRUD операции с инвентаризацией")
@Api(tags = "Inventarization Rest Controller")
@RequestMapping("/api/inventarization")
public class InventarizationRestController {

    private final InventarizationService inventarizationService;
    private final CheckEntityService checkEntityService;

    public InventarizationRestController(InventarizationService inventarizationService,
                                         CheckEntityService checkEntityService) {
        this.inventarizationService = inventarizationService;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех инвентаризаций")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка инвентаризаций"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InventarizationDto>> getAll() {
        return ResponseEntity.ok(inventarizationService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение конкретной инвентаризации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение инвентаризации"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти инвентаризацию")
                                                          @PathVariable(name = "id") Long id)  {
        checkEntityService.checkExistsInventarizationById(id);

        return ResponseEntity.ok(inventarizationService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой инвентаризации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Инвентаризация создана"),
            @ApiResponse(code = 201, message = "Запрос принят и инвентаризация добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationDto> create(@ApiParam(name = "inventarizationDto",
            value = "DTO инвентаризации, которое необходимо создать")
                                                @RequestBody InventarizationDto inventarizationDto) {

        return ResponseEntity.ok(inventarizationService.create(inventarizationDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об инвентаризации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об инвентаризации обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об инвентаризации обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationDto> update(@ApiParam(name = "inventarizationDto",
            value = "DTO инвентаризации, которое необходимо обновить")
                                                         @RequestBody InventarizationDto inventarizationDto) {

        return ResponseEntity.ok(inventarizationService.update((inventarizationDto)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление инвентаризации по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Инвентаризация удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить инвентаризацию")
                                                             @PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsInventarizationById(id);
        inventarizationService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
