package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.InvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Invoice Rest Controller", description = "CRUD  операции с накладными")
@Api(tags = "Invoice Rest Controller")
@RequestMapping("/api/invoice")
public class InvoiceRestController {
    private final InvoiceService invoiceService;
    private final CheckEntityService checkEntityService;

    public InvoiceRestController(InvoiceService invoiceService,
                                 CheckEntityService checkEntityService) {
        this.invoiceService = invoiceService;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех накладных")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка накладных"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoiceDto>> getAll(@RequestParam(required = false) String typeOfInvoice) {
        List<InvoiceDto> invoiceDtoList;
        if (typeOfInvoice != null) {
            invoiceDtoList = invoiceService.getAll(typeOfInvoice);
        } else {
            invoiceDtoList = invoiceService.getAll();
        }
        return ResponseEntity.ok(invoiceDtoList);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка счетов по заданным параметрам")
    public ResponseEntity<List<InvoiceDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = GreaterThanOrEqual.class),
                    @Spec(path = "typeOfInvoice", params = "typeOfInvoice", spec = Equal.class),
                    @Spec(path = "company.name", params = "companyDto", spec = Like.class),
                    @Spec(path = "contractor.name", params = "contractorDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "warehouse.name", params = "warehouseDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "isSpend", params = "spend", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
            }) Specification<Invoice> spec) {
        return ResponseEntity.ok(invoiceService.search(spec));
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "searchByString", notes = "Search of invoices")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка накладных"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoiceDto>> search(@RequestParam("search") String search,
                                                   @RequestParam("typeOfInvoice") TypeOfInvoice typeOfInvoice) {
        return ResponseEntity.ok(invoiceService.findBySearchAndTypeOfInvoice(search, typeOfInvoice));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение накладной по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Накладная найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoiceDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти накладную")
                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsInvoiceById(id);
        return ResponseEntity.ok(invoiceService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой накладной")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Накладная создана"),
            @ApiResponse(code = 201, message = "Запрос принят и накладная добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoiceDto> create(@ApiParam(name = "invoiceDto", value = "DTO накладной, которую необходимо создать")
                                             @RequestBody InvoiceDto invoiceDto) {
        return ResponseEntity.ok().body(invoiceService.create(invoiceDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о накладной")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о накладной обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о накладной обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "invoiceDto", value = "DTO накладной, которую необходимо обновить")
                                    @RequestBody InvoiceDto invoiceDto) {
        return ResponseEntity.ok().body(invoiceService.update(invoiceDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление накладной по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Накладная удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить накладную")
                                        @PathVariable(name = "id") Long id) {
        invoiceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
