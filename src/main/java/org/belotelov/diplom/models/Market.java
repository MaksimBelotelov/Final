package org.belotelov.diplom.models;

import jakarta.persistence.*;
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
    @OneToOne
    @JoinColumn(name="account")
    private Account account;
}
