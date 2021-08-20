package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.MovementDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.MovementService;
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
@Tag(name = "Movement Rest Controller", description = "CRUD  операции с перемещениями товаров")
@Api(tags = "Movement Rest Controller")
@RequestMapping("/api/movement")
public class MovementRestController {

    private final MovementService movementService;
    private final CheckEntityService checkEntityService;

    public MovementRestController(MovementService movementService, CheckEntityService checkEntityService) {
        this.movementService = movementService;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех перемещений")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка перемещений"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<MovementDto>> getAll() {
        return ResponseEntity.ok(movementService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение списка всех перемещений")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка перемещений"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти перемещение")
                                                 @PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsMovementById(id);

        return ResponseEntity.ok(movementService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового перемещения")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Перемещение создано"),
            @ApiResponse(code = 201, message = "Запрос принят и перемещение добавлено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> create(@ApiParam(name = "movementDto",
            value = "DTO перемещения, которое необходимо создать")
                                                @RequestBody MovementDto movementDto) {
        return ResponseEntity.ok(movementService.create(movementDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о перемещении")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о перемещении обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о перемещении обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> update(@ApiParam(name = "movementDto",
            value = "DTO перемещения, которую необходимо обновить")
                                                @RequestBody MovementDto movementDto) {
        return ResponseEntity.ok(movementService.update(movementDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление перемещения по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Перемещение удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить перемещение")
                                                    @PathVariable("id") Long id) {
        checkEntityService.checkExistsMovementById(id);
        movementService.deleteById(id);

        return ResponseEntity.ok().build();
    }


}
