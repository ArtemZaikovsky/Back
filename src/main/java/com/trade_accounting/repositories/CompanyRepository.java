package com.trade_accounting.repositories;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    Company getCompanyByBankAccounts(BankAccount bankAccount);

    Company findCompanyByEmail(String email);

    Company getCompaniesById(Long id);
}
