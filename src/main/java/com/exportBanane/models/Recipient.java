package com.exportBanane.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "recipient")
public class Recipient {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String address;

    private String city;

    private String country;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Order> order;
}
