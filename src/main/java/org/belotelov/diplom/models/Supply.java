package org.belotelov.diplom.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supply")
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supplydate")
    private LocalDate supplyDate = LocalDate.now();
    @NotNull(message = "Поле не может быть пустым")
    @ManyToOne
    private Market market;
    @JsonManagedReference
    @OneToMany(mappedBy = "supply")
    private List<SupplyItem> supplyItems;
    private Boolean processed;
}
