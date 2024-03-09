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
@Table(name = "sale")
public class Sale {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "saledate")
        private LocalDate saledate = LocalDate.now();
        @ManyToOne
        private Market market;
        @JsonManagedReference
        @OneToMany(mappedBy = "sale")
        private List<SaleItem> saleItems;
        @Column(name = "total")
        private Double total;
        private Boolean processed;
}

