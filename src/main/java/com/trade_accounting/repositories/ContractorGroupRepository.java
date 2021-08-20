package com.trade_accounting.repositories;

import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ContractorGroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractorGroupRepository extends JpaRepository<ContractorGroup, Long> {

    Optional<ContractorGroup> findByName(String name);

    @Query("select new com.trade_accounting.models.dto.ContractorGroupDto(" +
            "cg.id, " +
            "cg.name, " +
            "cg.sortNumber" +
            ") " +
            "from ContractorGroup cg")
    List<ContractorGroupDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ContractorGroupDto(" +
            "cg.id, " +
            "cg.name, " +
            "cg.sortNumber" +
            ") " +
            "from ContractorGroup cg " +
            "where cg.id = :id")
    ContractorGroupDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ContractorGroupDto(" +
            "cg.contractorGroup.id, " +
            "cg.contractorGroup.name, " +
            "cg.contractorGroup.sortNumber" +
            ") " +
            "from Contractor cg " +
            "where cg.id = :id")
    ContractorGroupDto getContractorGroupByContractorId(@Param("id") Long id);

    ContractorGroup getContractorGroupById(Long id);


//    default Optional<ContractorGroup> getEntityName(String name) {
//        return null;
//    }

    //JpaEntityInformation<ContractorGroup, Long>,
}
