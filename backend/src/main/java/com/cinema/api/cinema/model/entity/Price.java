package com.cinema.api.cinema.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {

     @Column(nullable = false, name = "price")
     private BigDecimal value;

     @Column(nullable = false)
     @Size(max = 10)
     @Enumerated(EnumType.STRING)
     private Currency currency;
}
