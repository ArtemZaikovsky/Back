package com.trade_accounting.services.impl;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.services.interfaces.PaymentService;
import com.trade_accounting.utils.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final ProjectRepository projectRepository;
    private final PaymentMapper paymentMapper;


    @Override
    public List<PaymentDto> getAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentMapper.toDto(
                paymentRepository.findById(id).orElse(new Payment())
        );
    }

    @Override
    public PaymentDto create(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toModel(paymentDto);

        payment.setCompany(
                companyRepository.findById(
                        paymentDto.getCompanyId()
                ).orElse(null)
        );

        payment.setContractor(
                contractorRepository.findById(
                        paymentDto.getContractorId()
                ).orElse(null)
        );

        payment.setContract(
                contractRepository.findById(
                        paymentDto.getContractId()
                ).orElse(null)
        );

        payment.setProject(
                projectRepository.findById(
                        paymentDto.getProjectId()
                ).orElse(null)
        );

        LocalDateTime time = LocalDateTime.parse(paymentDto.getTime().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        payment.setTime(time);
        return paymentMapper.toDto(paymentRepository.save(payment));
    }


    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        return create(paymentDto);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentDto> search(String search) {
        return paymentRepository.search(search).stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> filter(Specification<Payment> specification) {
        return paymentRepository.findAll(specification).stream().
                map(paymentMapper::toDto).collect(Collectors.toList());
    }
}
