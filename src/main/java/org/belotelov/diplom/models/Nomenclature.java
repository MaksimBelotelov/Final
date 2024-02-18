package org.belotelov.diplom.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "nomenclature")
public class Nomenclature {
    @Id
    @Column(name = "code")
    private Integer code;
    @Column(name = "title")
    private String title;
    @Column(name = "opt_price")
    private Double optPrice;
    @Column(name = "price")
    private Double price;
    @ManyToOne
    private Category category;
}
