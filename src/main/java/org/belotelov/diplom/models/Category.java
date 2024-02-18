package org.belotelov.diplom.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    private Long id;
    @Column(name = "title")
    private String title;
}
