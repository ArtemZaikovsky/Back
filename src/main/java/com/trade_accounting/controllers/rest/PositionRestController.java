package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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
@Tag(name = "Position Rest Controller", description = "CRUD операции с должностями")
@Api(tags = "Position Rest Controller")
@RequestMapping("/api/position")
public class PositionRestController {

    private final PositionService positionService;
    private final CheckEntityService checkEntityService;

    public PositionRestController(PositionService positionService,
                                  CheckEntityService checkEntityService) {
        this.positionService = positionService;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех должностей")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка должностей"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PositionDto>> getAll() {
        List<PositionDto> positions = positionService.getAll();
        return ResponseEntity.ok(positions);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение должности по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Должность найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PositionDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти должность")
                                               @PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsPositionById(id);
        return ResponseEntity.ok(positionService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение новой должности")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Должность создана"),
            @ApiResponse(code = 201, message = "Запрос принят и должность добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "PositionDto", value = "DTO должности, которую необходимо создать")
                                    @RequestBody PositionDto positionDto) {
        return ResponseEntity.ok().body(positionService.create(positionDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о должности")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о должности обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о должности обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "PositionDto", value = "DTO должности, которую необходимо обновить")
                                    @RequestBody PositionDto positionDto) {
        return ResponseEntity.ok().body(positionService.update(positionDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление должности по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Должность удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить должность")
                                        @PathVariable(name = "id") Long id) {
        positionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
