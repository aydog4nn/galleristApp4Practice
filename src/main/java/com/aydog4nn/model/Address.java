package com.aydog4nn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "neigborhood")
    private String neighborhood;

    @Column(name = "street")
    private String street;

}
