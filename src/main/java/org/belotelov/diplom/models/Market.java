package org.belotelov.diplom.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "market")
public class Market {
    @Id
    private Integer id;
    @Column(name = "address")
    private String address;
    @Column(name = "employee")
    private String employee;
}
