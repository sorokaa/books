package io.srk.dictionary.model.category.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "dictionary", name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
}
