package org.belotelov.diplom.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "inventorydate")
    private LocalDate inventoryDate = LocalDate.now();
    @ManyToOne
    private Market market;
    @JsonManagedReference
    @OneToMany(mappedBy = "inventory")
    private List<InventoryItem> inventoryItems;
    private Boolean processed;
}


