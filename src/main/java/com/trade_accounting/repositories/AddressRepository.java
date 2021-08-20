package com.trade_accounting.repositories;

import com.trade_accounting.models.Address;
import com.trade_accounting.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address getAddressById(Long id);
}
