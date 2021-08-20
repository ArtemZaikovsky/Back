package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.repositories.InternalOrderRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.InternalOrderService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author Pavel Andrusov
 * @since 19.07.2021
 */

@RestController
@Tag(name = "Internal Order Rest Controller", description = "CRUD  операции со внутренними заказами")
@Api(tags = "Internal Order Rest Controller")
@RequestMapping("/api/internalorder")
@RequiredArgsConstructor
public class InternalOrderRestController {
    private final CheckEntityService checkEntityService;
    private final InternalOrderService internalOrderService;
    private final InternalOrderRepository internalOrderRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех внутренних заказов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка внутренних заказов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InternalOrderDto>> getAll() {
        return ResponseEntity.ok(internalOrderService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение внутреннего заказа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение свнутреннего заказа по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InternalOrderDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти внутренний заказ")
                                                    @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) internalOrderRepository, id);

        return ResponseEntity.ok(internalOrderService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового внутреннего заказа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Внутренний заказ создан"),
            @ApiResponse(code = 201, message = "Запрос принят и внутренний заказ добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InternalOrderDto> create(@ApiParam(name = "internalOrderDto",
            value = "DTO внутреннего заказа, которое необходимо создать")
                                                   @RequestBody InternalOrderDto internalOrderDto) {
        return ResponseEntity.ok(internalOrderService.create(internalOrderDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о внутреннем заказе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о внутреннем заказе обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о внутреннем заказе обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InternalOrderDto> update(@ApiParam(name = "internalOrderDto",
            value = "DTO внутреннего заказа, которую необходимо обновить")
                                                   @RequestBody InternalOrderDto internalOrderDto) {
        return ResponseEntity.ok(internalOrderService.update(internalOrderDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление внутреннего заказа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Внутренний заказ удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InternalOrderDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить внутренний заказ")
                                                       @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) internalOrderRepository, id);
        internalOrderService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}