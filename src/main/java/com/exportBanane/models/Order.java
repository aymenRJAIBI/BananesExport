package com.exportBanane.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate createdDate;

    private LocalDate deliveryDate;

    private Integer quantity;

    private Double price;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;
}
