package org.belotelov.diplom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Market market;
    @ManyToOne
    private Nomenclature nomenclature;
    private Integer quantity;
}
