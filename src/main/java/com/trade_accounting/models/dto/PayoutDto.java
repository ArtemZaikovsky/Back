package com.trade_accounting.models.dto;


import com.trade_accounting.models.Company;
import com.trade_accounting.models.RetailStore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayoutDto {

    Long id;

    String date;

    Long retailStoreId;

    String whoWasPaid;

    Long companyId;

    Boolean isSent;

    Boolean isPrint;

    String comment;
}
