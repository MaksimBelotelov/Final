package org.belotelov.diplom.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import lombok.Data;

@Entity
@Data
@Table(name = "nomenclature")
public class Nomenclature {
    @Id
    @Column(name = "code")
    @NotNull(message = "Поле не может быть пустым")
    @Digits(message = "Длина кода не более 6 символов", integer = 6, fraction = 0)
    private Integer code;
    @NotBlank(message = "Введите название")
    @Column(name = "title")
    private String title;
    @NotNull(message = "Поле не может быть пустым")
    @Column(name = "opt_price")
    private Double optPrice;
    @Column(name = "price")
    @NotNull(message = "Поле не может быть пустым")
    private Double price;
    @ManyToOne
    @NotNull(message = "Поле не может быть пустым")
    private Category category;
}
