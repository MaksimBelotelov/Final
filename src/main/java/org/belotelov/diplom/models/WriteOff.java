package org.belotelov.diplom.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "writeoff")
public class WriteOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "writeoffdate")
    private LocalDate writeOffDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "market")
    private Market market;
    @ManyToOne
    @JoinColumn(name = "nomenclature")
    private Nomenclature nomenclature;
    @Column(name = "reason")
    private String reason;
}