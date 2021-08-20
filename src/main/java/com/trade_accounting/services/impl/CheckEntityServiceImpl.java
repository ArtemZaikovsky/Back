package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.BadRequestException;
import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.AcceptanceProductionRepository;
import com.trade_accounting.repositories.AcceptanceRepository;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.repositories.AttributeOfCalculationObjectRepository;
import com.trade_accounting.repositories.BalanceAdjustmentRepository;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.CorrectionProductRepository;
import com.trade_accounting.repositories.CorrectionRepository;
import com.trade_accounting.repositories.CurrencyRepository;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.InventarizationProductRepository;
import com.trade_accounting.repositories.InventarizationRepository;
import com.trade_accounting.repositories.InvoiceProductRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.MovementProductRepository;
import com.trade_accounting.repositories.MovementRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.ProductGroupRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.repositories.RemainRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.repositories.ReturnToSupplierRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.repositories.SupplierAccountRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.repositories.TaxSystemRepository;
import com.trade_accounting.repositories.TechnicalCardGroupRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckEntityServiceImpl implements CheckEntityService {
    private final UnitRepository unitRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ImageRepository imageRepository;
    private final RoleRepository roleRepository;
    private final WarehouseRepository warehouseRepository;
    private final TaskRepository taskRepository;
    private final TaskCommentRepository taskCommentRepository;
    private final ContractorGroupRepository contractorGroupRepository;
    private final CurrencyRepository currencyRepository;
    private final CompanyRepository companyRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final BankAccountRepository bankAccountRepository;
    private final RetailStoreRepository retailStoreRepository;
    private final TypeOfPriceRepository typeOfPriceRepository;
    private final ProjectRepository projectRepository;
    private final ProductGroupRepository productGroupRepository;
    private final TaxSystemRepository taxSystemRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductRepository invoiceProductRepository;
    private final ContractRepository contractRepository;
    private final ContractorRepository contractorRepository;
    private final PaymentRepository paymentRepository;
    private final AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;
    private final SupplierAccountRepository supplierAccountRepository;
    private final TechnicalCardGroupRepository technicalCardGroupRepository;
    private final TechnicalCardRepository technicalCardRepository;
    private final CorrectionRepository correctionRepository;
    private final RemainRepository remainRepository;
    private final CorrectionProductRepository correctionProductRepository;
    private final AddressRepository addressRepository;
    private final ReturnToSupplierRepository returnToSupplierRepository;
    private final InventarizationRepository inventarizationRepository;
    private final InventarizationProductRepository inventarizationProductRepository;
    private final BalanceAdjustmentRepository balanceAdjustmentRepository;
    private final AcceptanceRepository acceptanceRepository;
    private final AcceptanceProductionRepository acceptanceProductionRepository;
    private final MovementProductRepository movementProductRepository;
    private final MovementRepository movementRepository;

    @Override
    public void checkExistsUnitById(Long unitId) {
        if (!unitRepository.existsById(unitId)) {
            throw new NotFoundEntityException("Ед. измерения с id=" + unitId + ", не найдена");
        }
    }

    @Override
    public void checkExistsEmployeeById(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new NotFoundEntityException("Сотрудника с id=" + employeeId + ", не найдено");
        }
    }

    @Override
    public void checkExistsTypeOfContractorById(Long typeOfContractorId) {
        if (!typeOfContractorRepository.existsById(typeOfContractorId)) {
            throw new NotFoundEntityException("Контрагента с id=" + typeOfContractorId + ", не найдено");
        }
    }

    @Override
    public void checkExistsProductById(Long productGroupId) {
        if (!productGroupRepository.existsById(productGroupId)) {
            throw new NotFoundEntityException("Продукта с id=" + productGroupId + ", не найдено");
        }
    }

    @Override
    public void checkExistsProjectById(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new NotFoundEntityException("Проекта с id=" + projectId + ", не найдено");
        }
    }

    @Override
    public void checkExistsWarehouseById(Long warehouseId) {
        if (!warehouseRepository.existsById(warehouseId)) {
            throw new NotFoundEntityException("Склада с id=" + warehouseId + ", не найдено");
        }
    }

    @Override
    public void checkExistsAttributeOfCalculationObjectByID(Long attributeOfCalculationObjectId) {
        if (!attributeOfCalculationObjectRepository.existsById(attributeOfCalculationObjectId)) {
            throw new NotFoundEntityException("Склада с id=" + attributeOfCalculationObjectId + ", не найдено");
        }
    }

    @Override
    public void checkForBadEmployee(EmployeeDto employee) {
        DepartmentDto department = employee.getDepartmentDto();
        PositionDto position = employee.getPositionDto();
        ImageDto image = employee.getImageDto();
        Set<RoleDto> roles = employee.getRoleDto();

        boolean isDepartmentFilled = department != null && department.getId() != null;
        boolean isPositionFilled = position != null && position.getId() != null;
        boolean isImageFilled = image != null && image.getId() != null;
        boolean rolesFilled = roles != null && !roles.isEmpty();

        if (isDepartmentFilled && !departmentRepository.existsById(department.getId())) {
            throw new BadRequestException(
                    String.format("Подразделения с id %d не существует", department.getId())
            );
        }

        if (isPositionFilled && !positionRepository.existsById(position.getId())) {
            throw new BadRequestException(
                    String.format("Должности с id %d не существует", position.getId())
            );
        }

        if (isImageFilled && !imageRepository.existsById(image.getId())) {
            throw new BadRequestException(
                    String.format("Изображения с id %d не существует", image.getId())
            );
        }

        if (rolesFilled) {
            for (RoleDto role : roles) {
                boolean isRoleFilled = role != null && role.getId() != null;

                if (isRoleFilled && !roleRepository.existsById(role.getId())) {
                    throw new BadRequestException(
                            String.format("Роли с id %d не существует.", role.getId())
                    );
                }
            }
        }
    }

    @Override
    public void checkExistsTaskById(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundEntityException("Задача с id=" + taskId + ", не найдена");
        }
    }

    @Override
    public void checkExistsTaskCommentById(Long taskCommentId) {
        if (!taskCommentRepository.existsById(taskCommentId)) {
            throw new NotFoundEntityException("Комментарий с id=" + taskCommentId + ", не найден");
        }
    }

    @Override
    public void checkExistsContractorGroupById(Long contractorGroupId) {
        if (!contractorGroupRepository.existsById(contractorGroupId)) {
            throw new NotFoundEntityException("Группа контрагентов с id=" + contractorGroupId + " не найдена");
        }
    }

    @Override
    public void checkExistsCurrencyById(Long currencyId) {
        if (!currencyRepository.existsById(currencyId)) {
            throw new NotFoundEntityException("Валюта с id=" + currencyId + ", не найдена");
        }
    }

    @Override
    public void checkExistCompanyById(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new NotFoundEntityException("Компания с id=" + companyId + " не найдена");
        }
    }

    @Override
    public void checkExistsCorrectionById(Long correctionId) {
        if (!correctionRepository.existsById(correctionId)) {
            throw new NotFoundEntityException("Оприходование с id=" + correctionId + ", не найдено");
        }
    }

    @Override
    public void checkForBadCompany(CompanyDto company) {

        if (company.getLegalDetailDtoId() != 0 &&
                legalDetailRepository.getById(company.getLegalDetailDtoId()) == null) {
            throw new BadRequestException(
                    String.format("Юридических деталей с id %d не существует", company.getLegalDetailDtoId())
            );
        }

        if (!company.getBankAccountDtoIds().isEmpty()) {
            company.getBankAccountDtoIds().stream()
                    .forEach(
                            id -> {
                                if (bankAccountRepository.getById(id) == null) {
                                    throw new BadRequestException(
                                            String.format("Банковского счёта с id %d не существует.", id));
                                }
                            }
                    );
        }
    }

    @Override
    public void checkExistsRetailStoreById(Long retailStoreId) {
        if (!retailStoreRepository.existsById(retailStoreId)) {
            throw new NotFoundEntityException("Точка продаж с id=" + retailStoreId + ", не найдена");
        }
    }

    @Override
    public void checkExistsProductGroupById(Long productGrouprId) {
        if (!productGroupRepository.existsById(productGrouprId)) {
            throw new NotFoundEntityException("Группа товаров с id=" + productGrouprId + ", не найдена");
        }
    }


    @Override
    public void checkExistsTypeofPriceByID(Long typeOfPriceId) {
        if (!typeOfPriceRepository.existsById(typeOfPriceId)) {
            throw new NotFoundEntityException("Тип цен с id=" + typeOfPriceId + ", не найдены");
        }
    }

    @Override
    public void checkExistsPositionById(Long positionId) {
        if (!positionRepository.existsById(positionId)) {
            throw new NotFoundEntityException("Список видов цен с id=" + positionId + ", не найден");
        }
    }

    @Override
    public void checkExistsTaxSystemById(Long taxSystemId) {
        if (!taxSystemRepository.existsById(taxSystemId)) {
            throw new NotFoundEntityException("Налоговая система с id=" + taxSystemId + ", не найдена");
        }
    }

    @Override
    public void checkExistsRoleById(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new NotFoundEntityException("Роли с id=" + roleId + ", не найдены");
        }
    }

    @Override
    public void checkExistsPaymentById(Long paymentId) {
        if (!paymentRepository.existsById(paymentId)) {
            throw new NotFoundEntityException("Оплата с id=" + paymentId + ", не найдена");
        }
    }

    @Override
    public void checkExistsInvoiceById(Long invoiceId) {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new NotFoundEntityException("Счет с id=" + invoiceId + ", не найден");
        }
    }

    @Override
    public void checkExistsInvoiceProductById(Long invoiceProductId) {
        if (!invoiceProductRepository.existsById(invoiceProductId)) {
            throw new NotFoundEntityException("Счета-фактура с id=" + invoiceProductId + ", не найдена");
        }
    }

    @Override
    public void checkExistsLegalDetailById(Long legalDetailId) {
        if (!legalDetailRepository.existsById(legalDetailId)) {
            throw new NotFoundEntityException("LegalDetail с id=" + legalDetailId + ", не найдена");
        }
    }

    @Override
    public void checkExistsImageById(Long imageId) {
        if (!imageRepository.existsById(imageId)) {
            throw new NotFoundEntityException("Картинка с id=" + imageId + ", не найдена");
        }
    }

    @Override
    public void checkExistsDepartmentById(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new NotFoundEntityException("Отдел с id=" + departmentId + ", не найден");
        }
    }

    @Override
    public void checkExistsContractById(Long contractId) {
        if (!contractRepository.existsById(contractId)) {
            throw new NotFoundEntityException("Контракт с id=" + contractId + ", не найден");
        }
    }

    @Override
    public void checkExistsContractorById(Long contractorId) {
        if (!contractorRepository.existsById(contractorId)) {
            throw new NotFoundEntityException("Подрядчик с id=" + contractorId + ", не найден");
        }
    }

    @Override
    public void checkExistsBankAccountById(Long bankAccountId) {
        if (!bankAccountRepository.existsById(bankAccountId)) {
            throw new NotFoundEntityException("Банковский счет с id=" + bankAccountId + ", не найден");
        }
    }

    @Override
    public void checkExistsSupplierAccountById(Long supplierAccountId) {
        if (!supplierAccountRepository.existsById(supplierAccountId)) {
            throw new NotFoundEntityException("Счет поставщика с id=" + supplierAccountId + ", не найден");
        }
    }

    @Override
    public void checkExistsTechnicalCardById(Long id) {
        if (!technicalCardRepository.existsById(id)) {
            throw new NotFoundEntityException("Тех. карта с id=" + id + ", не найдена");
        }
    }

    @Override
    public void checkExistsTechnicalCardGroupById(Long technicalCardGroupId) {
        if (!technicalCardGroupRepository.existsById(technicalCardGroupId)) {
            throw new NotFoundEntityException("Группа технических карт с id=" + technicalCardGroupId + ", не найдена");
        }
    }

    @Override
    public void checkExistsPostingProductById(Long id) {

    }

    @Override
    public void checkExistsAddressById(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new NotFoundEntityException("Адрес с id=" + id + ", не найден");
        }
    }

    @Override
    public void checkExistsRemainById(Long id) {
        if (!remainRepository.existsById(id)) {
            throw new NotFoundEntityException("Остаток с id=" + id + ", не найден");
        }
    }

    @Override
    public void checkExistsCorrectionProductById(Long correctionProduct) {
        if (!correctionProductRepository.existsById(correctionProduct)) {
            throw new NotFoundEntityException("Товар для корректировки остатков с id=" + correctionProduct + "не найден");
        }
    }

    @Override
    public void checkExistsReturnToSupplierById(Long returnToSupplier) {
        if (!returnToSupplierRepository.existsById(returnToSupplier)) {
            throw new NotFoundEntityException("Возврат поставщика с id=" + returnToSupplier + "не найден");
        }
    }

    @Override
    public void checkExistsInventarizationById(Long inventarizationId) {
        if (!inventarizationRepository.existsById(inventarizationId)) {
            throw new NotFoundEntityException("Возврат инвентаризации с id=" + inventarizationId + "не найден");
        }
    }

    @Override
    public void checkExistsInventarizationProductById(Long inventarizationProduct) {
        if (!inventarizationProductRepository.existsById(inventarizationProduct)) {
            throw new NotFoundEntityException("Товар для инвентаризации с id=" + inventarizationProduct + "не найден");
        }
    }

    @Override
    public void checkExistsBalanceAdjustmentById(Long balanceAdjustment) {
        if (!balanceAdjustmentRepository.existsById(balanceAdjustment)) {
            throw new NotFoundEntityException("Корректировка баланса с id=" + balanceAdjustment + "не найден");
        }
    }

    @Override
    public void checkExistsMovementById(Long id) {
        if (!movementRepository.existsById(id)) {
            throw new NotFoundEntityException("Перемещние с id " + id + " не найдено");
        }
    }

    @Override
    public void checkExistsMovementProductById(Long id) {
        if (!movementProductRepository.existsById(id)) {
            throw new NotFoundEntityException("Перемещние продукта с id " + id + " не найдено");
        }
    }

    @Override
    public void checkExistsAcceptanceById(Long id) {
        if (!acceptanceRepository.existsById(id)) {
            throw new NotFoundEntityException("Приемка с id=" + id + "не найдена");
        }
    }

    @Override
    public void checkExistsAcceptanceProductionById(Long id) {
        if (!acceptanceProductionRepository.existsById(id)) {
            throw new NotFoundEntityException("Приемка товара с id=" + id + "не найдена");
        }
    }

    @Override
    public void checkExists(JpaRepository<Entity, Long> repository, Long id) {
        String repositoryName = repository.getClass().getInterfaces()[0].getSimpleName();

        if (!repository.existsById(id)) {
            throw new NotFoundEntityException(
                     "Запись с id=" + id + " не найдена в репозитории " + repositoryName
            );
        }
    }
}
