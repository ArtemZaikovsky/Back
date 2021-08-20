package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.dto.PaymentDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PaymentService extends AbstractService<PaymentDto> {

    List<PaymentDto> filter(Specification<Payment> specification);

    List<PaymentDto> search(String search);
}
