package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

public interface CheckEntityService {
    void checkExistsUnitById(Long unitId);

    void checkExistsEmployeeById(Long employeeId);

    void checkExistsTypeOfContractorById(Long typeOfContractorId);

    void checkExistsProductById(Long productGroupId);

    void checkExistsProjectById(Long projectId);

    void checkExistsWarehouseById(Long warehouseId);

    void checkExistsAttributeOfCalculationObjectByID(Long attributeOfCalculationObjectId);

    void checkForBadEmployee(EmployeeDto employee);

    void checkExistsTaskById(Long taskId);

    void checkExistsTaskCommentById(Long taskCommentId);

    void checkExistsContractorGroupById(Long contractorGroupId);

    void checkExistsCurrencyById(Long currencyId);

    void checkExistCompanyById(Long companyId);

    void checkForBadCompany(CompanyDto company);

    void checkExistsRetailStoreById(Long RetailStoreId);

    void checkExistsProductGroupById(Long productGrouprId);

    void checkExistsTypeofPriceByID(Long typeOfPriceId);

    void checkExistsPositionById(Long positionId);

    void checkExistsTaxSystemById(Long taxSystemId);

    void checkExistsRoleById(Long roleId);

    void checkExistsPaymentById(Long paymenId);

    void checkExistsInvoiceById(Long invoiceId);

    void checkExistsInvoiceProductById(Long invoiceProductId);

    void checkExistsLegalDetailById(Long legalDetailId);

    void checkExistsImageById(Long imageId);

    void checkExistsDepartmentById(Long departmentId);

    void checkExistsContractById(Long contractId);

    void checkExistsContractorById(Long contractorId);

    void checkExistsBankAccountById(Long bankAccountId);

    void checkExistsSupplierAccountById(Long supplierAccountId);

    void checkExistsTechnicalCardById(Long id);

    void checkExistsTechnicalCardGroupById(Long id);

    void checkExistsPostingProductById(Long id);

    void checkExistsAddressById(Long id);

    void checkExistsCorrectionById(Long correctionId);

    void checkExistsAcceptanceById(Long id);

    void checkExistsAcceptanceProductionById(Long id);

    void checkExistsRemainById(Long id);

    void checkExistsCorrectionProductById(Long correctionProduct);

    void checkExistsReturnToSupplierById(Long returnToSupplier);

    void checkExistsInventarizationById(Long inventarizationId);

    void checkExistsInventarizationProductById(Long inventarizationProduct);

    void checkExistsBalanceAdjustmentById(Long balanceAdjustment);

    void checkExistsMovementById(Long id);

    void checkExistsMovementProductById(Long id);

    void checkExists(JpaRepository<Entity, Long> repository, Long id);
}
