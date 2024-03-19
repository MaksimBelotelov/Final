package org.belotelov.diplom.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "market")
public class Market {
    @Id
    @NotNull(message = "Поле не может быть пустым")
    private Integer id;
    @Column(name = "address")
    @NotBlank(message = "Введите адрес")
    private String address;
    @Column(name = "employee")
    private String employee;
    @OneToOne
    @JoinColumn(name="account")
    private Account account;
}
